
package com.tacomall.common.libs.aliyun;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.tacomall.common.config.SmsConfig;
import com.tacomall.common.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AliyunSMS<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String signName = "TACOMALL";

    private String templateCode = "";

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public void send(String mobile, T sendBo) throws Exception {
        if (StringUtil.isBlank(this.templateCode)) {
            throw new Exception("TemplateCode is required, call func setTemplateCode first please before sending sms~");
        }
        com.aliyun.dysmsapi20170525.Client smsClient = SmsConfig.getSmsClient();
        logger.info("=========== mobile =========");
        logger.info(mobile);
        logger.info("=========== SMSSendRewardBo ======");
        logger.info(JSON.toJSONString(sendBo));
        logger.info("============================");
        SendSmsRequest sendReq = new SendSmsRequest().setPhoneNumbers(mobile).setSignName(this.signName)
                .setTemplateCode(this.templateCode).setTemplateParam(JSON.toJSONString(sendBo));
        SendSmsResponse sendResp = smsClient.sendSms(sendReq);
        String code = sendResp.body.code;
        if (!com.aliyun.teautil.Common.equalString(code, "OK")) {
            throw new Exception(sendResp.body.message);
        }
    }

}