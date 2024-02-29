package com.tacomall.apiadmin.service.impl;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.tacomall.apiadmin.service.LogisticService;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.form.FormOrderMapper;
import com.tacomall.common.mapper.logistic.LogisticMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.vo.base.PaginatorVo;

@Service
public class LogisticServiceImpl extends ServiceImpl<LogisticMapper, Logistic> implements LogisticService {

  @Autowired
  Environment environment;

  @Autowired
  FormOrderMapper formOrderMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<Logistic> add(JSONObject json) {
    ResponseJson<Logistic> responseJson = new ResponseJson<>();
    responseJson.setStatus(false);
    Logistic logistic = JSON.toJavaObject(json, Logistic.class);
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      baseMapper.insert(logistic);
      if (formOrderMapper.update(null, new UpdateWrapper<FormOrder>()
          .lambda()
          .set(FormOrder::getStatus, "pending")
          .eq(FormOrder::getLogisticId, logistic.getId())
          .eq(FormOrder::getStatus, "initial")) <= 0) {
        ExceptionUtil.throwServerException("订单不存在");
      }
      dataSourceTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwServerException(e.getMessage());
    }
    responseJson.ok();
    responseJson.setData(logistic);
    return responseJson;
  }

}
