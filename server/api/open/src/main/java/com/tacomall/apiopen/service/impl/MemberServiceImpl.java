package com.tacomall.apiopen.service.impl;

import java.io.ByteArrayInputStream;
import java.util.UUID;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.tacomall.common.config.WxMaConfiguration;
import com.tacomall.apiopen.service.MemberService;
import com.tacomall.apiopen.config.OssConfig;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.member.MemberMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.StringUtil;
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper,  Member> implements MemberService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String WX_APPID = "wx0038a1d24bb9eb99";

    public static final String PAGES = "pages/index/index";

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    private Environment env;

    @Override
    public ResponseJson<String> inviteCode(Integer memberId, Integer isForceUpdate) throws WxErrorException {
        ResponseJson<String> responseJson = new ResponseJson<>();
        OSSClient ossClient = OssConfig.getOSSClient();
        String inviteCode = "";
        Member member = memberMapper.selectById(memberId);
        if (ObjectUtil.isNull(member)) {
            ExceptionUtil.throwClientException("会员不存在");
        }
        if (isForceUpdate.equals(1) || (ObjectUtil.isNotNull(member) && StringUtil.isEmpty(member.getInviteCode()))) {
            WxMaService wxService = WxMaConfiguration.getMaService(WX_APPID);
            WxMaCodeLineColor lineColor = new WxMaCodeLineColor("255", "255", "255");
            byte[] bytes = wxService.getQrcodeService().createWxaCodeUnlimitBytes(
                    "a=invite&env=" + env.getProperty("env") + "&b=" + member.getId(), PAGES, false, inviteCode, 430, false,
                    lineColor, false);
            String filename = UUID.randomUUID().toString();
            ossClient.putObject(OssConfig.bucket, filename, new ByteArrayInputStream(bytes));
            inviteCode = new StringBuilder().append("//").append(OssConfig.host).append("/").append(filename).toString();
            memberMapper.update(null, new UpdateWrapper<Member>().lambda()
                    .eq(Member::getId, member.getId())
                    .set(Member::getInviteCode, inviteCode));
        }
        inviteCode = StringUtil.isNotBlank(inviteCode) ? inviteCode : member.getInviteCode();
        responseJson.setData(inviteCode);
        responseJson.ok();
        return responseJson;
    }


}
