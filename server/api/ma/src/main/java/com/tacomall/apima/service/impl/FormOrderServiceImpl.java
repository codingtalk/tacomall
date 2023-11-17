package com.tacomall.apima.service.impl;

import java.math.BigDecimal;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result.JsapiResult;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.service.WxPayService;

import com.tacomall.apima.dto.MemberUpdateDto;
import com.tacomall.apima.service.FormOrderService;
import com.tacomall.apima.vo.form.FormOrderAddVo;
import com.tacomall.common.annotation.SimpleRestLogin;
import com.tacomall.common.bo.form.FormOrderInfoDataBo;
import com.tacomall.common.entity.form.FormPay;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.entity.logistic.LogisticType;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.form.FormOrderMapper;
import com.tacomall.common.mapper.form.FormPayMapper;
import com.tacomall.common.mapper.logistic.LogisticMapper;
import com.tacomall.common.mapper.logistic.LogisticTypeMapper;
import com.tacomall.common.mapper.member.MemberMapper;
import com.tacomall.common.properties.WxPayProperties;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.RequestUtil;
import com.tacomall.common.util.SnUtil;
import com.tacomall.common.vo.base.PaginatorVo;

@Service
public class FormOrderServiceImpl extends ServiceImpl<FormOrderMapper, FormOrder> implements FormOrderService {
  @Autowired
  LogisticMapper logisticMapper;

  @Autowired
  LogisticTypeMapper logisticTypeMapper;

  @Autowired
  FormPayMapper formPayMapper;

  @Autowired
  WxPayService wxPayService;

  @Autowired
  WxPayProperties wxProperties;

  @Autowired
  MemberMapper memberMapper;

  @Autowired
  Environment environment;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  @SimpleRestLogin
  public ResponseJson<FormOrderAddVo> add(JSONObject json) {
    ResponseJson<FormOrderAddVo> responseJson = new ResponseJson<>();
    String type = RequestUtil.getStringParam("type");
    SnUtil snUtil = new SnUtil();
    FormOrder formOrder = JSON.toJavaObject(json, FormOrder.class);
    FormOrderInfoDataBo formOrderInfoDataBo = formOrder.getInfoData();
    LogisticType logisticType = logisticTypeMapper.selectOne(
        new QueryWrapper<LogisticType>().lambda().eq(LogisticType::getValue,
            formOrderInfoDataBo.getLogistic()));
    Member member = memberMapper
        .selectOne(new QueryWrapper<Member>().lambda().eq(Member::getId,
            RequestUtil.getLoginUser().getInteger("id")));
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      BigDecimal amountEstimate = BigDecimal.ZERO;
      BigDecimal amountActual = BigDecimal.ZERO;
      FormPay formPay = null;
      FormOrderAddVo formOrderAddVo = new FormOrderAddVo();
      formOrder = formOrder.toBuilder()
          .type(type)
          .sn(snUtil.gen(1))
          .memberId(member.getId())
          .status("initial")
          .amountEstimate(BigDecimal.ZERO)
          .amountEstimate(BigDecimal.ZERO)
          .build();
      baseMapper.insert(formOrder);

      // @TODO to calculate amount

      formPay = FormPay.builder().sn(snUtil.gen(2)).type("WX").fee(amountActual).status("initial")
          .build();
      formPayMapper.insert(formPay);

      JsapiResult jsapiResult = wxPayService.createOrderV3(TradeTypeEnum.JSAPI,
          new WxPayUnifiedOrderV3Request().setOutTradeNo(formPay.getSn())
              .setAmount(new WxPayUnifiedOrderV3Request.Amount()
                  .setTotal(formPay.getFee().multiply(new BigDecimal(100)).intValue()))
              .setNotifyUrl(wxProperties.getNotifyUrl())
              .setDescription("i快寄下单")
              .setPayer(new WxPayUnifiedOrderV3Request.Payer().setOpenid(member.getOpenId())));
      formOrderAddVo.setJsapiResult(jsapiResult);

      baseMapper.update(null, new UpdateWrapper<FormOrder>().lambda()
          .eq(FormOrder::getId, formOrder.getId())
          .set(FormOrder::getFormPayId, formPay.getId())
          .set(FormOrder::getAmountEstimate, amountEstimate)
          .set(FormOrder::getAmountActual, amountActual));
      formPayMapper.update(null, new UpdateWrapper<FormPay>().lambda()
          .eq(FormPay::getId, formPay.getId())
          .set(FormPay::getStatus, "created"));
      formOrderAddVo.setFormOrder(formOrder);
      responseJson.setData(formOrderAddVo);
      responseJson.ok();
      dataSourceTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwServerException(e.toString());
    }
    return responseJson;
  }

  @Override
  @SimpleRestLogin
  public ResponseJson<FormOrder> info(JSONObject json) {
    ResponseJson<FormOrder> responseJson = new ResponseJson<>();
    Integer id = RequestUtil.getIntegerParam("id");
    responseJson.setData(baseMapper
        .selectOne(
            new QueryWrapper<FormOrder>().lambda().eq(FormOrder::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  @SimpleRestLogin
  public ResponsePageJson<List<PaginatorVo>> page(JSONObject json) {
    ResponsePageJson<List<PaginatorVo>> responsePaginatorVo = new ResponsePageJson<>();
    Integer pageIndex = RequestUtil.getIntegerParam("pageIndex");
    Integer pageSize = RequestUtil.getIntegerParam("pageSize");
    Page<FormOrder> page = new Page<>(pageIndex, pageSize);
    QueryWrapper<FormOrder> qw = new QueryWrapper<FormOrder>();

    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query)) {
      if (ObjectUtil.isNotNull(query.getString("status"))) {
        qw.in("oform.status", Arrays.asList(query.getString("status").split(",")));
      }
      if (ObjectUtil.isNotNull(query.getString("type"))) {
        qw.in("oform.type", Arrays.asList(query.getString("type").split(",")));
      }
    }
    qw.eq("oform.member_id", RequestUtil.getLoginUser().getInteger("id"));
    qw.eq("oform.is_delete", 0);
    IPage<PaginatorVo> result = baseMapper.queryPage(page, qw);
    responsePaginatorVo.setData(result.getRecords());
    responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePaginatorVo.ok();
    return responsePaginatorVo;
  }

}
