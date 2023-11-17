package com.tacomall.apima.service.impl;

import java.io.ByteArrayInputStream;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import cn.binarywang.wx.miniapp.util.WxMaConfigHolder;
import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;

import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.apima.dto.MemberUpdateDto;
import com.tacomall.apima.service.MemberService;
import com.tacomall.common.annotation.SimpleRestLogin;
import com.tacomall.common.bo.member.MemberChainIdsBo;
import com.tacomall.common.config.WxMaConfiguration;
import com.tacomall.common.constant.RedisKeyConstant;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.entity.member.MemberWallet;
import com.tacomall.common.entity.org.OrgStaff;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.member.MemberMapper;
import com.tacomall.common.mapper.member.MemberWalletMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.JwtUtil;
import com.tacomall.common.util.PasswordUtil;
import com.tacomall.common.util.RedisUtil;
import com.tacomall.common.util.RequestUtil;
import com.tacomall.common.vo.base.PaginatorVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@Service
@AllArgsConstructor
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberWalletMapper walletMapper;

    @Autowired
    Environment environment;

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public ResponseJson<Member> register(String mobile, String passwd, String inviteCode, Integer type) {
        ResponseJson<Member> responseJson = new ResponseJson<>();
        Member memberInvite = baseMapper
                .selectOne(new QueryWrapper<Member>().lambda().eq(Member::getInviteCode, inviteCode));
        if (ObjectUtil.isNull(memberInvite)) {
            ExceptionUtil.throwBizException("邀请会员不存在");
        }
        String formatInvite = "00000000";
        List<MemberChainIdsBo> chainIds = new ArrayList<>();
        chainIds.addAll(memberInvite.getChainIds());
        chainIds.add(MemberChainIdsBo.builder().memberId(memberInvite.getId()).updateTime(LocalDateTime.now()).build());
        Member memberRegister = new Member();
        memberRegister.setInviteMemberId(memberInvite.getId());
        memberRegister.setMobile(mobile);
        memberRegister.setPasswd(PasswordUtil.encode(passwd));
        memberRegister.setChainIds(chainIds);
        memberRegister.setInviteCode(new DecimalFormat(formatInvite)
                .format(redisUtil.incrBy(RedisKeyConstant.MA_MEMBER_REGISTER_LAST_INVITE_CODE, 1, 0)));
        baseMapper.insert(memberRegister);
        responseJson.setData(memberRegister);
        responseJson.ok();
        return responseJson;
    }

    @Override
    public ResponseJson<String> loginByMobile(String mobile, String passwd) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        Member member = baseMapper.selectOne(new QueryWrapper<Member>().lambda().eq(Member::getMobile, mobile)
                .eq(Member::getPasswd, PasswordUtil.encode(passwd)));
        if (ObjectUtil.isNull(member)) {
            responseJson.setMessage("账号或密码错误");
            return responseJson;
        }
        try {
            JwtUtil ju = new JwtUtil();
            ju.setISSUER(environment.getProperty("spring.application.name"));
            responseJson.setData(ju.create(new HashMap<String, String>() {
                {
                    put("id", member.getId().toString());
                }
            }));
            responseJson.ok();
        } catch (Exception e) {
            ExceptionUtil.throwServerException("token生成失败");
        }
        return responseJson;
    }

    @Override
    public ResponseJson<Map<String, Object>> wxLogin(JSONObject json) {
        ResponseJson<Map<String, Object>> responseJson = new ResponseJson<>();
        String appid = RequestUtil.getStringParam("appid");
        String code = RequestUtil.getStringParam("code");
        Integer invite = json.getInteger("invite");
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        if (StringUtils.isBlank(code)) {
            ExceptionUtil.throwClientException("empty jscode");
        }
        if (!wxMaService.switchover(appid)) {
            ExceptionUtil.throwServerException(String.format("未找到对应appid=[%s]的配置，请核实！", appid));
        }
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            Boolean isNew = false;
            JwtUtil ju = new JwtUtil();
            Map<String, String> claims = new HashMap<>();
            Map<String, Object> data = new HashMap<>();
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            Member member = baseMapper.selectOne(new QueryWrapper<Member>()
                    .lambda()
                    .eq(Member::getOpenId, session.getOpenid()));
            if (ObjectUtil.isNull(member)) {
                isNew = true;
                Member inviteMember = baseMapper.selectById(invite);
                if (ObjectUtil.isNull(inviteMember)) {
                    ExceptionUtil.throwBizException("邀请会员不存在");
                }
                List<MemberChainIdsBo> chainIds = new ArrayList<>();
                chainIds.addAll(inviteMember.getChainIds());
                chainIds.add(MemberChainIdsBo.builder().memberId(inviteMember.getId()).updateTime(LocalDateTime.now())
                        .build());
                member = Member.builder()
                        .inviteMemberId(invite)
                        .inviteUpperMemberId(inviteMember.getInviteMemberId())
                        .chainIds(chainIds)
                        .openId(session.getOpenid()).build();
                baseMapper.insert(member);
                walletMapper.insert(MemberWallet.builder().memberId(member.getId()).build());
            }
            ju.setISSUER(environment.getProperty("spring.application.name"));
            claims.put("id", member.getId().toString());
            data.put("token", ju.create(claims));
            data.put("isNew", isNew);
            responseJson.setData(data);
            responseJson.ok();
            dataSourceTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            ExceptionUtil.throwServerException(e.getMessage());
        } finally {
            WxMaConfigHolder.remove();
        }
        return responseJson;
    }

    @Override
    @SimpleRestLogin
    public ResponseJson<Member> info(JSONObject json) {
        ResponseJson<Member> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper
                .selectOne(new QueryWrapper<Member>().lambda().eq(Member::getId,
                        RequestUtil.getLoginUser().getInteger("id"))));
        responseJson.ok();
        return responseJson;
    }

    @Override
    @SimpleRestLogin
    public ResponseJson<String> update(JSONObject json) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        MemberUpdateDto memberUpdateDto = JSON.toJavaObject(json, MemberUpdateDto.class);
        Member member = new Member();
        BeanUtils.copyProperties(memberUpdateDto, member);
        baseMapper.updateById(member.toBuilder().id(RequestUtil.getLoginUser().getInteger("id")).build());
        responseJson.setData("更新成功");
        responseJson.ok();
        return responseJson;
    }

    @Override
    @SimpleRestLogin
    public ResponsePageJson<List<PaginatorVo>> page(JSONObject json) {
        ResponsePageJson<List<PaginatorVo>> responsePaginatorVo = new ResponsePageJson<>();
        Integer pageIndex = RequestUtil.getIntegerParam("pageIndex");
        Integer pageSize = RequestUtil.getIntegerParam("pageSize");
        Page<Member> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<Member> qw = new QueryWrapper<Member>();

        JSONObject query = json.getJSONObject("query");
        if (ObjectUtil.isNotEmpty(query)) {
            if (ObjectUtil.isNotNull(query.getInteger("inviteMemberId"))) {
                qw.eq("m.invite_member_id", query.getInteger("inviteMemberId"));
            }
        }
        qw.eq("m.is_delete", 0);
        IPage<PaginatorVo> result = baseMapper.queryPage(page, qw);
        responsePaginatorVo.setData(result.getRecords());
        responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
        responsePaginatorVo.ok();
        return responsePaginatorVo;
    }

}