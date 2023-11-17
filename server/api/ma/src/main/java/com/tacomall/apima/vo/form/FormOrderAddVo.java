package com.tacomall.apima.vo.form;

import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result.JsapiResult;
import com.tacomall.common.entity.form.FormOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class FormOrderAddVo {

  private FormOrder formOrder;

  private JsapiResult jsapiResult;
}
