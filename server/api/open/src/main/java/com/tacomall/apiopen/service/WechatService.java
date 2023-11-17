package com.tacomall.apiopen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.form.FormPay;
import com.tacomall.common.json.ResponseJson;

public interface WechatService extends IService<FormPay> {

    ResponseJson<String> payNotify(String outTradeNo);
}
