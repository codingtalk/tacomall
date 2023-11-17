
package com.tacomall.apiopen.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.notify.SignatureHeader;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyV3Response;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyV3Result.DecryptNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.tacomall.apiopen.service.AliyunService;
import com.tacomall.apiopen.service.WechatService;
import com.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/notify")
public class NotifyController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  WxPayService wxPayService;

  @Autowired
  AliyunService aliyunService;

  @Autowired
  WechatService wechatService;

  @PostMapping("ossUpload")
  public ResponseJson<Object> ossUpload(HttpServletRequest request) {
    return aliyunService.OSSUploadNotify(request);
  }

  @PostMapping("wecahtPay")
  public String wecahtPay(@RequestHeader("Wechatpay-Signature") String WechatpaySignature,
      @RequestHeader("Wechatpay-Serial") String WechatpaySerial,
      @RequestHeader("Wechatpay-Timestamp") String WechatpayTimestamp,
      @RequestHeader("Wechatpay-Nonce") String WechatpayNonce,
      @RequestBody JSONObject json) throws WxPayException {
    logger.info("--------- wxNotifyResult ------------");
    logger.info(json.toJSONString());
    SignatureHeader signatureHeader = new SignatureHeader();
    signatureHeader.setSignature(WechatpaySignature);
    signatureHeader.setSerial(WechatpaySerial);
    signatureHeader.setTimeStamp(WechatpayTimestamp);
    signatureHeader.setNonce(WechatpayNonce);
    final DecryptNotifyResult notifyResult = this.wxPayService
        .parseOrderNotifyV3Result(json.toJSONString(), null)
        .getResult();
    wechatService.payNotify(notifyResult.getOutTradeNo());
    return WxPayNotifyV3Response.success("成功");
  }

}
