/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2022-01-03 16:23:08
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/impl/MemberServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.service.impl;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.core.env.Environment;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import store.tacomall.apima.service.MemberService;
import store.tacomall.common.entity.member.Member;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.member.MemberMapper;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.common.util.PasswordUtil;
import store.tacomall.common.util.RedisUtil;
import store.tacomall.common.util.RequestUtil;
import store.tacomall.common.constant.RedisKeyConstant;
import store.tacomall.common.bo.member.MemberChainIdsBo;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    public ResponseJson<Member> info(JSONObject json) {
        ResponseJson<Member> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper
                .selectOne(new QueryWrapper<Member>().lambda().eq(Member::getId,
                        RequestUtil.getLoginUser().getString("id"))));
        responseJson.ok();
        return responseJson;
    }

}
