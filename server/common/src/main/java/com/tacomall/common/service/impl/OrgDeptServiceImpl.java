
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
import com.tacomall.common.entity.org.OrgDept;
import com.tacomall.common.enumeration.BizEnum;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.org.OrgDeptMapper;
import com.tacomall.common.service.OrgDeptService;
import com.tacomall.common.util.ExceptionUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

@Service
public class OrgDeptServiceImpl extends ServiceImpl<OrgDeptMapper, OrgDept> implements OrgDeptService {

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponsePageJson<List<OrgDept>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<OrgDept>> responsePaginatorVo = new ResponsePageJson<>();
    Page<OrgDept> page = new Page<>(pageIndex, pageSize);
    LambdaQueryWrapper<OrgDept> q = new QueryWrapper<OrgDept>().lambda();
    if (ObjectUtil.isNotEmpty(json.getJSONObject("query"))
        && ObjectUtil.isNotEmpty(json.getJSONObject("query").get("keyword"))) {
      q.like(OrgDept::getName, json.getJSONObject("query").get("keyword"));
    }
    q.eq(OrgDept::getIsDelete, 0);
    IPage<OrgDept> result = baseMapper.queryPage(page, q);
    responsePaginatorVo.setData(result.getRecords());
    responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePaginatorVo.ok();
    return responsePaginatorVo;
  }

  @Override
  public ResponseJson<OrgDept> info(Integer id) {
    ResponseJson<OrgDept> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper.selectOne(new QueryWrapper<OrgDept>()
        .lambda()
        .eq(OrgDept::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<OrgDept> add(JSONObject json) {
    ResponseJson<OrgDept> responseJson = new ResponseJson<>();
    OrgDept orgDept = JSON.toJavaObject(json, OrgDept.class);
    baseMapper.insert(orgDept);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<OrgDept> update(Integer id, JSONObject json) {
    ResponseJson<OrgDept> responseJson = new ResponseJson<>();
    OrgDept orgDept = JSON.toJavaObject(json, OrgDept.class);
    orgDept.setId(id);
    baseMapper.updateById(orgDept);
    responseJson.ok();
    return responseJson;
  }

}
