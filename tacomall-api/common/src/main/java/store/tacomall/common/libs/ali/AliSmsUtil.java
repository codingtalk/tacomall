/***
 * @Author: 码上talk|RC
 * @Date: 2021-06-25 19:15:59
 * @LastEditTime: 2021-10-10 17:43:54
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/common/src/main/java/store/tacomall/common/libs/ali/AliSmsUtil.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.common.libs.ali;

import com.alibaba.fastjson.JSON;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import store.tacomall.common.config.SmsConfig;
import store.tacomall.common.util.StringUtil;

public class AliSmsUtil<T> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String signName = "塔可商城平台";

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
        logger.info("=========== smsSendRewardBo ======");
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