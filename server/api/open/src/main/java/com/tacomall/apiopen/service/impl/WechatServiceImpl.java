package com.tacomall.apiopen.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.tacomall.apiopen.service.WechatService;
import com.tacomall.common.entity.form.FormPay;
import com.tacomall.common.entity.form.FormRefund;
import com.tacomall.common.bo.form.FormOrderInfoDataBo;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.exceptionInterceptor.exception.ServerException;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.form.FormOrderMapper;
import com.tacomall.common.mapper.form.FormPayMapper;
import com.tacomall.common.mapper.form.FormRefundMapper;
import com.tacomall.common.mapper.logistic.LogisticMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.SnUtil;

import cn.hutool.core.util.ObjectUtil;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@Service
public class WechatServiceImpl extends ServiceImpl<FormPayMapper, FormPay> implements WechatService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  WxPayService wxPayService;

  @Autowired
  FormOrderMapper formOrderMapper;

  @Autowired
  FormRefundMapper formRefundMapper;

  @Autowired
  LogisticMapper logisticMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<String> payNotify(String outTradeNo) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    FormPay formPay = baseMapper.selectOne(new QueryWrapper<FormPay>().lambda()
        .eq(FormPay::getSn, outTradeNo)
        .eq(FormPay::getStatus, "created"));
    if (ObjectUtil.isNull(formPay)) {
      return responseJson;
    }
    FormOrder formOrder = formOrderMapper.selectOne(new QueryWrapper<FormOrder>().lambda()
        .eq(FormOrder::getFormPayId, formPay.getId()));
    FormOrderInfoDataBo formOrderInfoDataBo = formOrder.getInfoData();
    Logistic logistic = null;
    baseMapper.update(null, new UpdateWrapper<FormPay>().lambda()
        .eq(FormPay::getId, formPay.getId())
        .set(FormPay::getStatus, "payed"));
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      // @TODO to create third-part order

      formOrderMapper.update(null, new UpdateWrapper<FormOrder>().lambda()
          .eq(FormOrder::getId, formOrder.getId())
          .set(FormOrder::getLogisticId, ObjectUtil.isNull(logistic) ? null : logistic.getId())
          .set(FormOrder::getStatus, "placed"));

      dataSourceTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      logger.error("===============> errors from third-part：", e);
      Integer refundFee = formPay.getFee().multiply(new BigDecimal(100)).intValue();
      FormRefund formRefund = FormRefund.builder()
          .sn(new SnUtil().gen(3))
          .type("WX")
          .fee(formPay.getFee())
          .status("created").build();
      formRefundMapper.insert(formRefund);
      try {
        this.wxPayService.refundV3(new WxPayRefundV3Request()
            .setOutRefundNo(formRefund.getSn())
            .setOutTradeNo(outTradeNo)
            .setAmount(new WxPayRefundV3Request.Amount().setTotal(refundFee).setRefund(refundFee)
                .setCurrency("CNY")));
        baseMapper.update(null, new UpdateWrapper<FormPay>().lambda()
            .eq(FormPay::getId, formPay.getId())
            .set(FormPay::getStatus, "refund"));
        formRefundMapper.update(null, new UpdateWrapper<FormRefund>().lambda()
            .eq(FormRefund::getId, formRefund.getId())
            .set(FormRefund::getStatus, "completed"));
        formOrderMapper.update(null, new UpdateWrapper<FormOrder>().lambda()
            .eq(FormOrder::getId, formOrder.getId())
            .set(FormOrder::getStatus, "refund"));
      } catch (WxPayException wxPayException) {
        logger.error("========== refund failed =========== \n", wxPayException);
        logger.error(wxPayException.toString());
        baseMapper.update(null, new UpdateWrapper<FormPay>().lambda()
            .eq(FormPay::getId, formPay.getId())
            .set(FormPay::getStatus, "refundFailed"));
        formRefundMapper.update(null, new UpdateWrapper<FormRefund>().lambda()
            .eq(FormRefund::getId, formRefund.getId())
            .set(FormRefund::getStatus, "failed"));
      }
    }
    responseJson.setData("ok");
    responseJson.ok();
    return responseJson;
  }
}
