package com.tacomall.common.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.common.constant.approve.ApproveConstant;
import com.tacomall.common.entity.approve.Approve;
import com.tacomall.common.entity.approve.ApproveNode;
import com.tacomall.common.entity.approve.ApproveProcess;
import com.tacomall.common.entity.approve.ApproveType;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.SqlMapper;
import com.tacomall.common.mapper.approve.ApproveMapper;
import com.tacomall.common.mapper.approve.ApproveNodeMapper;
import com.tacomall.common.mapper.approve.ApproveProcessMapper;
import com.tacomall.common.mapper.approve.ApproveTypeMapper;
import com.tacomall.common.service.ApproveService;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.RequestUtil;
import com.tacomall.common.util.SnUtil;
import com.tacomall.common.util.StringUtil;
import com.tacomall.common.vo.base.PaginatorVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import cn.hutool.core.util.ObjectUtil;

@Service
public class ApproveServiceImpl extends ServiceImpl<ApproveMapper, Approve> implements ApproveService {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  Environment environment;

  @Autowired
  ApproveMapper approveMapper;

  @Autowired
  ApproveTypeMapper approveTypeMapper;

  @Autowired
  ApproveNodeMapper approveNodeMapper;

  @Autowired
  ApproveProcessMapper approveProcessMapper;

  @Autowired
  SqlMapper sqlMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<PaginatorVo>> responsePageJson = new ResponsePageJson<>();
    Page<Approve> page = new Page<>(pageIndex, pageSize);
    QueryWrapper<Approve> qw = new QueryWrapper<Approve>();
    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query)) {
      if (ObjectUtil.isNotEmpty(query.getInteger("type"))) {
        qw.eq("a.type_id", query.getInteger("type"));
      }
      if (ObjectUtil.isNotEmpty(query.getString("keyword"))) {
        qw.like("a.name", query.getString("keyword"));
      }
      if (ObjectUtil.isNotEmpty(query.getInteger("status"))) {
        qw.eq("a.status", query.getInteger("status"));
      }
    }
    qw.eq("a.is_delete", 0);
    qw.orderByDesc("a.create_time");
    IPage<PaginatorVo> result = baseMapper.queryPage(page, qw, new HashMap<String, Object>() {
      {
        put("type", query.getInteger("type"));
      }
    });
    responsePageJson.ok();
    responsePageJson.setData(result.getRecords());
    responsePageJson.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    return responsePageJson;
  }

  @Override
  public ResponseJson<Approve> add(Integer typeId, Integer relateId) {
    ResponseJson<Approve> responseJson = new ResponseJson<>();
    ApproveType approveType = approveTypeMapper.selectById(typeId);
    Optional<ApproveNode> optFirstApproveNode = approveNodeMapper.selectList(
        new QueryWrapper<ApproveNode>().lambda().eq(ApproveNode::getTypeId, typeId))
        .stream().findFirst();
    ApproveNode firstApproveNode = null;
    JSONObject firstApproveNodeOpt = null;
    if (ObjectUtil.isNull(approveType)) {
      ExceptionUtil.throwServerException("类型不存在");
    }
    if (!optFirstApproveNode.isPresent()) {
      ExceptionUtil.throwServerException("类型未设置流程");
    }
    firstApproveNode = optFirstApproveNode.get();
    firstApproveNodeOpt = firstApproveNode.getOpt();
    if (ObjectUtil.isNull(firstApproveNodeOpt)
        || StringUtil.isNullOrEmpty(firstApproveNodeOpt.getString("table"))
        || StringUtil.isNullOrEmpty(firstApproveNodeOpt.getJSONObject("hook")
            .getString("add"))) {
      ExceptionUtil.throwServerException("类型未配置信息");
    }
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      Approve approve = Approve.builder().sn(new SnUtil().gen(4))
          .creatorId(1)
          .typeId(typeId)
          .nodeId(optFirstApproveNode.get().getId())
          .status("pending").build();
      baseMapper.insert(approve);
      sqlMapper.updateExecute(
          String.format(
              "update %s set status = '%s', approve_ids = concat_ws(',', '%s', ifnull(approve_ids, '')) where id = %s",
              firstApproveNodeOpt.getString("table"),
              firstApproveNodeOpt.getJSONObject("hook").getString("add"),
              approve.getId(), relateId));
      responseJson.setData(approve);
      responseJson.ok();
      dataSourceTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwServerException(e.toString());
    }
    return responseJson;
  }

  @Override
  public ResponseJson<Approve> info(Integer id) {
    ResponseJson<Approve> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper.queryInfo(new QueryWrapper<Approve>().lambda().eq(Approve::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<String> doAgree(Integer id, Integer isAgree, JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    Approve approve = baseMapper.selectById(id);
    List<ApproveNode> approveNodeList = approveNodeMapper
        .selectList(new QueryWrapper<ApproveNode>().lambda().eq(ApproveNode::getTypeId, approve.getTypeId()));
    String nextStatus = ApproveConstant.STATUS_PENDDING;
    ApproveNode currentApproveNode = null;
    ApproveNode nextApproveNode = null;
    JSONObject currentApproveNodeOpt = null;
    Integer approveNodeIdx = 0;
    if (ObjectUtil.isNull(approve) || !approve.getStatus().equals(ApproveConstant.STATUS_PENDDING)) {
      ExceptionUtil.throwServerException("审核不存在");
    }
    if (approveNodeList.size() == 0) {
      ExceptionUtil.throwServerException("审核未设置节点");
    }
    currentApproveNode = approveNodeList.stream().filter(x -> x.getId().equals(approve.getNodeId())).findFirst()
        .get();
    approveNodeIdx = Arrays.binarySearch(approveNodeList.stream().map(i -> i.getId()).toArray(),
        approve.getNodeId());
    currentApproveNodeOpt = currentApproveNode.getOpt();
    if (isAgree.equals(1)) {
      if (approveNodeList.size() - 1 == approveNodeIdx) {
        nextStatus = ApproveConstant.STATUS_PASSED;
      } else {
        nextApproveNode = approveNodeList.get(approveNodeIdx + 1);
      }
    } else {
      if (approveNodeIdx.equals(0)) {
        nextStatus = ApproveConstant.STATUS_UNPASSED;
      } else {
        nextApproveNode = approveNodeList.get(approveNodeIdx - 1);
      }
    }
    TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
    try {
      approveProcessMapper.insert(
          ApproveProcess.builder().approveId(id).nodeId(approve.getNodeId())
              .checkerId(Optional.ofNullable(RequestUtil.getLoginUser()).flatMap(x -> {
                return Optional.of(x.getInteger("id"));
              }).orElse(1))
              .status(isAgree.equals(1) ? ApproveConstant.STATUS_PASSED : ApproveConstant.STATUS_UNPASSED)
              .build());
      baseMapper.update(null, new UpdateWrapper<Approve>().lambda().eq(Approve::getId, id)
          .set(Approve::getStatus, nextStatus)
          .set(ObjectUtil.isNotNull(nextApproveNode), Approve::getNodeId,
              ObjectUtil.isNotNull(nextApproveNode) ? nextApproveNode.getId() : null)
          .set(Approve::getExecutorId, json.getInteger("executorId")));

      if (ObjectUtil.isNotNull(currentApproveNodeOpt)
          && StringUtil.isNotBlank(currentApproveNodeOpt.getString("table"))
          && ObjectUtil.isNotNull(currentApproveNodeOpt.getJSONObject("hook"))) {
        String status = null;
        JSONObject hook = currentApproveNodeOpt.getJSONObject("hook");
        JSONArray SQLs = currentApproveNodeOpt.getJSONArray("SQLs");
        if (isAgree.equals(1)) {
          status = hook.getString("passed");
        } else if (isAgree.equals(0)) {
          status = hook.getString("reject");
        }
        if (ObjectUtil.isNotNull(status)) {
          sqlMapper.updateExecute(
              String.format(
                  "update %s set status = '%s' where approve_ids REGEXP '^%s,|,%s,|^%s$|,%s$'",
                  currentApproveNodeOpt.getString("table"),
                  status,
                  approve.getId(), approve.getId(), approve.getId(), approve.getId()));
          if (ObjectUtil.isNotNull(SQLs)) {
            for (Object SQL : SQLs) {
              sqlMapper.updateExecute(
                  ((String) SQL).replace("#WHERE", String.format("where approve_ids REGEXP '^%s,|,%s,|^%s$|,%s$'",
                      approve.getId(), approve.getId(), approve.getId(), approve.getId())));
            }
          }
        }
      }
      responseJson.ok();
      responseJson.setData("操作正确");
      dataSourceTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      dataSourceTransactionManager.rollback(transactionStatus);
      ExceptionUtil.throwServerException(e.toString());
    }
    return responseJson;
  }

  @Override
  public ResponseJson<String> cancel(Integer id, JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    Approve approve = baseMapper.selectById(id);
    approveProcessMapper.insert(
        ApproveProcess.builder().approveId(id).nodeId(approve.getNodeId())
            .checkerId(Optional.ofNullable(RequestUtil.getLoginUser()).flatMap(x -> {
              return Optional.of(x.getInteger("id"));
            }).orElse(1)).status(ApproveConstant.STATUS_CANCEL).build());
    baseMapper.update(null, new UpdateWrapper<Approve>().lambda().eq(Approve::getId, id)
        .set(Approve::getStatus, ApproveConstant.STATUS_CANCEL).set(Approve::getNodeId, null));
    responseJson.ok();
    responseJson.setData("操作正确");
    return responseJson;
  }

}
