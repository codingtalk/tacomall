/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-22 14:58:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/service/impl/MemberServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen.service.impl;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.UUID;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaCodeLineColor;
import cn.hutool.core.util.ObjectUtil;
import com.aliyun.oss.OSSClient;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import store.tacomall.apiopen.service.MemberService;
import store.tacomall.common.config.OssConfig;
import store.tacomall.common.config.WxMaConfig;
import store.tacomall.common.entity.member.Member;
import store.tacomall.common.mapper.member.MemberMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.StringUtil;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  public static final String WX_APPID = "wx8a661b1b75f66322";

  public static final String PAGES = "pages/index/index";

  @Autowired
  MemberMapper memberMapper;

  @Autowired
  private Environment env;

  @Override
  public ResponseJson<String> inviteWxPic(Integer memberId, Integer isForceUpdate) throws WxErrorException {
    ResponseJson<String> responseJson = new ResponseJson<>();
    OSSClient ossClient = OssConfig.getOSSClient();
    String inviteWxPic = "";
    Member member = memberMapper.selectById(memberId);
    if (ObjectUtil.isNull(member)) {
      ExceptionUtil.throwClientException("会员不存在");
    }
    if (isForceUpdate == 1 || (ObjectUtil.isNotNull(member) && StringUtil.isEmpty(member.getInviteWxPic()))) {
      WxMaService wxService = WxMaConfig.getMaService(WX_APPID);
      WxMaCodeLineColor lineColor = new WxMaCodeLineColor("255", "255", "255");
      byte[] bytes = wxService.getQrcodeService().createWxaCodeUnlimitBytes(
          // type == 1 =>invite
          "type=1&env=" + env.getProperty("env") + "&code=" + member.getInviteCode(), PAGES, 430, false, lineColor,
          false);
      String filename = UUID.randomUUID().toString();
      ossClient.putObject(OssConfig.bucket, filename, new ByteArrayInputStream(bytes));
      inviteWxPic = new StringBuilder().append("//").append(OssConfig.host).append("/").append(filename).toString();
      memberMapper.update(null, new UpdateWrapper<Member>().lambda().eq(Member::getId, member.getId())
          .set(Member::getInviteWxPic, inviteWxPic));
    }
    inviteWxPic = StringUtil.isNotBlank(inviteWxPic) ? inviteWxPic : member.getInviteWxPic();
    responseJson.setData(inviteWxPic);
    responseJson.ok();
    return responseJson;
  }

}
