package com.tacomall.common.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.common.entity.org.OrgJob;
import com.tacomall.common.enumeration.BizEnum;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.org.OrgAccessRuleMapper;
import com.tacomall.common.mapper.org.OrgJobMapper;
import com.tacomall.common.service.OrgJobService;
import com.tacomall.common.util.ExceptionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

@Service
public class OrgJobServiceImpl extends ServiceImpl<OrgJobMapper, OrgJob> implements OrgJobService {

  @Autowired
  OrgAccessRuleMapper tmAccessRuleMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponsePageJson<List<OrgJob>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<OrgJob>> responsePageJson = new ResponsePageJson<>();
    Page<OrgJob> page = new Page<>(pageIndex, pageSize);
    LambdaQueryWrapper<OrgJob> q = new QueryWrapper<OrgJob>().lambda();
    if (ObjectUtil.isNotEmpty(json.getJSONObject("query"))
        && ObjectUtil.isNotEmpty(json.getJSONObject("query").get("keyword"))) {
      q.like(OrgJob::getName, json.getJSONObject("query").get("keyword"));
    }
    q.eq(OrgJob::getIsDelete, 0);
    IPage<OrgJob> result = baseMapper.selectPage(page, q);
    responsePageJson.setData(result.getRecords());
    responsePageJson.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePageJson.ok();
    return responsePageJson;
  }

  @Override
  public ResponseJson<OrgJob> info(Integer id) {
    ResponseJson<OrgJob> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper.selectOne(new QueryWrapper<OrgJob>()
        .lambda()
        .eq(OrgJob::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<OrgJob> add(JSONObject json) {
    ResponseJson<OrgJob> responseJson = new ResponseJson<>();
    responseJson.setStatus(false);
    OrgJob orgJob = JSON.toJavaObject(json, OrgJob.class);
    baseMapper.insert(orgJob);
    responseJson.setData(orgJob);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<OrgJob> update(Integer id, JSONObject json) {
    ResponseJson<OrgJob> responseJson = new ResponseJson<>();

    OrgJob orgJob = JSON.toJavaObject(json, OrgJob.class);
    baseMapper.updateById(orgJob);
    responseJson.setData(orgJob);
    responseJson.ok();
    return responseJson;
  }

}
