package com.tacomall.apiadmin.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.tacomall.apiadmin.service.MemberService;
import com.tacomall.apiadmin.vo.member.MemberInfoVo;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.member.MemberMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.vo.base.PaginatorVo;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

  @Autowired
  MemberMapper memberMapper;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Autowired
  TransactionDefinition transactionDefinition;

  private void updateStatus(int id, Integer value) {
    memberMapper.update(null,
        new UpdateWrapper<Member>().lambda().eq(Member::getId, id).set(Member::getStatus, value));
  }

  @Override
  public ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<PaginatorVo>> responsePaginatorVo = new ResponsePageJson<>();
    Page<Member> page = new Page<>(pageIndex, pageSize);
    QueryWrapper<Member> lqw = new QueryWrapper<Member>();
    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("type"))) {
      lqw.eq("m.type", query.get("type"));
    }
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("keyword"))) {
      lqw.and(k -> k.like("m.real_name", query.get("keyword")).or().eq("m.mobile", query.get("keyword")));
    }
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("status"))) {
      lqw.eq("m.status", query.getString("status"));
    }
    lqw.eq("m.is_delete", 0);
    IPage<PaginatorVo> result = baseMapper.queryPage(page, lqw);
    responsePaginatorVo.setData(result.getRecords());
    responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePaginatorVo.ok();
    return responsePaginatorVo;
  }

  @Override
  public ResponseJson<MemberInfoVo> info(Integer id) {
    ResponseJson<MemberInfoVo> responseJson = new ResponseJson<>();
    responseJson.setData(MemberInfoVo.builder()
        .member(baseMapper.queryInfo(new QueryWrapper<Member>().lambda().eq(Member::getId, id))).build());
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<String> updateField(Integer id, Integer type, JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      switch (type) {
        case 1:
          updateStatus(id, json.getInteger("value"));
          break;
        default:
          ExceptionUtil.throwClientException("非法参数：type");
      }
      dataSourceTransactionManager.commit(transactionStatus);
      responseJson.ok();
      responseJson.setData("更新成功");
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwSqlException(e.toString());
    }
    return responseJson;
  }

}