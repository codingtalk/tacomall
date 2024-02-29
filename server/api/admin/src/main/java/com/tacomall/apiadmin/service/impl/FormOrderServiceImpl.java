package com.tacomall.apiadmin.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import com.tacomall.apiadmin.dto.FormOrderUpdateDto;
import com.tacomall.apiadmin.service.FormOrderService;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.form.FormOrderMapper;
import com.tacomall.common.mapper.logistic.LogisticMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.vo.base.PaginatorVo;

@Service
public class FormOrderServiceImpl extends ServiceImpl<FormOrderMapper, FormOrder> implements FormOrderService {

  @Autowired
  LogisticMapper logisticMapper;

  @Autowired
  Environment environment;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<FormOrder> info(Integer id) {
    ResponseJson<FormOrder> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper
        .selectOne(
            new QueryWrapper<FormOrder>().lambda().eq(FormOrder::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<PaginatorVo>> responsePaginatorVo = new ResponsePageJson<>();
    Page<FormOrder> page = new Page<>(pageIndex, pageSize);
    QueryWrapper<FormOrder> qw = new QueryWrapper<FormOrder>();

    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query)) {
      if (ObjectUtil.isNotEmpty(query.get("keyword"))) {
        qw.like("oform.sn", query.get("keyword"));
      }
      if (ObjectUtil.isNotEmpty(query.getString("status"))) {
        qw.in("oform.status", Arrays.asList(query.getString("status").split(",")));
      }
    }
    qw.eq("oform.is_delete", 0);
    IPage<PaginatorVo> result = baseMapper.queryPage(page, qw);
    responsePaginatorVo.setData(result.getRecords());
    responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePaginatorVo.ok();
    return responsePaginatorVo;
  }

  @Override
  public ResponseJson<FormOrder> add(JSONObject json) {
    ResponseJson<FormOrder> responseJson = new ResponseJson<>();
    responseJson.setStatus(false);
    FormOrder formOrder = JSON.toJavaObject(json, FormOrder.class);
    baseMapper.insert(formOrder);
    responseJson.ok();
    responseJson.setData(formOrder);
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(Integer id, FormOrderUpdateDto formOrderUpdateDto) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    FormOrder formOrder = JSON
        .toJavaObject(JSONObject.parseObject(JSON.toJSONString(formOrderUpdateDto)), FormOrder.class)
        .toBuilder()
        .id(id).build();
    Logistic logistic = logisticMapper.selectOne(new QueryWrapper<Logistic>()
        .inSql("id", String.format("select logistic_id from order_form where id = %s", id)));
    if (ObjectUtil.isNotNull(logistic)) {
      // @TODO Cancel the order from third-part
    }
    baseMapper.updateById(formOrder);
    responseJson.ok();
    responseJson.setData("更新成功");
    return responseJson;
  }

}
