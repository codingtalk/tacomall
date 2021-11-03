/***
 * @Author: 码上talk|RC
 * @Date: 2021-08-02 14:33:53
 * @LastEditTime: 2021-10-10 21:29:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/service/impl/SmsServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiopen.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import store.tacomall.common.constant.RedisKeyConstant;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.libs.ali.AliSmsUtil;
import store.tacomall.common.libs.ali.bo.SmsSendRestPasswdBo;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.RedisUtil;
import cn.hutool.core.util.RandomUtil;
import store.tacomall.apiopen.service.SmsService;

@Service
public class SmsServiceImpl implements SmsService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String ALI_SMS_MEMBER_RESET_PASSWORD_CODE = "SMS_218585010";

  private final long REGISTER_MOBILE_CODE_EXPIRE_TIME = 60 * 5;

  @Autowired
  RedisUtil redisUtil;

  @Override
  public ResponseJson<String> send(String app, Integer bizType, String mobile) throws Exception {
    ResponseJson<String> responseJson = new ResponseJson<>();
    String code = RandomUtil.randomNumbers(4);

    switch (app) {
    case "ma":
      if (bizType == 1) {
        AliSmsUtil<SmsSendRestPasswdBo> resetPasswordAliSmsUtil = new AliSmsUtil<>();
        SmsSendRestPasswdBo smsSendRestPasswdBo = new SmsSendRestPasswdBo();
        smsSendRestPasswdBo.setCode(code);
        resetPasswordAliSmsUtil.setTemplateCode(ALI_SMS_MEMBER_RESET_PASSWORD_CODE);
        resetPasswordAliSmsUtil.send(mobile, smsSendRestPasswdBo);
        redisUtil.set(RedisKeyConstant.COMMON_MOBILE_VERIFY_CODE + mobile, code, 0, REGISTER_MOBILE_CODE_EXPIRE_TIME);
        logger.info("====== Member reset password code：" + RedisKeyConstant.COMMON_MOBILE_VERIFY_CODE + mobile);
      } else {
        ExceptionUtil.throwClientException("bizType type is error");
      }
      break;
    default:
      ExceptionUtil.throwClientException("app type is error");
    }
    responseJson.setData(code);
    responseJson.ok();
    return responseJson;
  }

}
