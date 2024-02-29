
package com.tacomall.apiopen.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import jakarta.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.tacomall.apiopen.config.OssConfig;
import com.tacomall.apiopen.service.AliyunService;
import com.tacomall.common.constant.RedisKeyConstant;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.libs.aliyun.AliyunSMS;
import com.tacomall.common.libs.aliyun.bo.SMSSendRestPasswdBo;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.RedisUtil;

import cn.hutool.core.util.RandomUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AliyunServiceImpl implements AliyunService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private final String ALI_SMS_MEMBER_RESET_PASSWORD_CODE = "SMS_218585010";

  private final long REGISTER_MOBILE_CODE_EXPIRE_TIME = 60 * 5;

  @Autowired
  RedisUtil redisUtil;

  @Override
  public ResponseJson<Map<String, Object>> OSSAuthorize(String dir, Integer insertDb) {
    ResponseJson<Map<String, Object>> responseJson = new ResponseJson<>();
    Map<String, Object> map = new HashMap<>();
    try {
      OSSClient client = OssConfig.getOSSClient();

      PolicyConditions policyConds = new PolicyConditions();
      policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
      policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

      long expireEndTime = System.currentTimeMillis() + OssConfig.expire * 1000;
      Date expiration = new Date(expireEndTime);
      String postPolicy = client.generatePostPolicy(expiration, policyConds);
      byte[] binaryData = postPolicy.getBytes("utf-8");
      String encodedPolicy = BinaryUtil.toBase64String(binaryData);
      String postSignature = client.calculatePostSignature(postPolicy);
      JSONObject jasonCallback = new JSONObject();
      jasonCallback.put("callbackUrl", OssConfig.callback);
      jasonCallback.put("callbackBody", "bucket=" + OssConfig.bucket + "&insertDb=" + insertDb
          + "&filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
      jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
      String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
      map.put("accessKey", OssConfig.accessKey);
      map.put("dir", dir);
      map.put("expire", OssConfig.expire);
      map.put("host", OssConfig.host);
      map.put("policy", encodedPolicy);
      map.put("signature", postSignature);
      map.put("callback", base64CallbackBody);
    } catch (Exception e) {
      this.logger.info("Storage authorize fail");
    }
    responseJson.setData(map);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<Object> OSSUploadNotify(HttpServletRequest request) {
    ResponseJson<Object> responseJson = new ResponseJson<>();
    Map<String, String> ossMap = new HashMap<>();
    ossMap.put("filename", request.getParameter("filename"));
    ossMap.put("bucket", request.getParameter("bucket"));
    ossMap.put("url", "//" + OssConfig.host + "/" + request.getParameter("filename"));
    responseJson.setData(ossMap);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<String> SMSSend(String app, Integer bizType, String mobile) throws Exception {
    ResponseJson<String> responseJson = new ResponseJson<>();
    String code = RandomUtil.randomNumbers(4);

    switch (app) {
      case "portal":
        if (bizType == 1) {
          AliyunSMS<SMSSendRestPasswdBo> resetPasswordAliSmsUtil = new AliyunSMS<>();
          SMSSendRestPasswdBo smsSendRestPasswdBo = new SMSSendRestPasswdBo();
          smsSendRestPasswdBo.setCode(code);
          resetPasswordAliSmsUtil.setTemplateCode(ALI_SMS_MEMBER_RESET_PASSWORD_CODE);
          resetPasswordAliSmsUtil.send(mobile, smsSendRestPasswdBo);
          redisUtil.set(RedisKeyConstant.COMMON_MOBILE_VERIFY_CODE + mobile, code, 0,
              REGISTER_MOBILE_CODE_EXPIRE_TIME);
          logger.info(
              "====== Member reset password code：" + RedisKeyConstant.COMMON_MOBILE_VERIFY_CODE + mobile);
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
