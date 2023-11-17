package com.tacomall.common.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.common.entity.approve.ApproveNode;
import com.tacomall.common.entity.approve.ApproveType;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.approve.ApproveNodeMapper;
import com.tacomall.common.mapper.approve.ApproveTypeMapper;
import com.tacomall.common.service.ApproveTypeService;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.vo.base.PaginatorVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

@Service
public class ApproveTypeServiceImpl extends ServiceImpl<ApproveTypeMapper, ApproveType>
        implements ApproveTypeService {

    @Autowired
    ApproveNodeMapper approveNodeMapper;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
        ResponsePageJson<List<PaginatorVo>> responsePageJson = new ResponsePageJson<>();
        Page<ApproveType> page = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<ApproveType> q = new QueryWrapper<ApproveType>().lambda();
        JSONObject query = json.getJSONObject("query");
        if (ObjectUtil.isNotEmpty(query)) {
            if (ObjectUtil.isNotEmpty(query.get("keyword"))) {
                q.like(ApproveType::getName, query.getString("keyword"));
            }
        }
        q.eq(ApproveType::getIsDelete, 0);
        IPage<PaginatorVo> result = baseMapper.queryPage(page, q);
        responsePageJson.setData(result.getRecords());
        responsePageJson.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
        responsePageJson.ok();
        return responsePageJson;
    }

    @Override
    public ResponseJson<ApproveType> info(Integer id) {
        ResponseJson<ApproveType> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper.queryInfo(new QueryWrapper<ApproveType>().lambda()
                .eq(ApproveType::getId, id)
                .eq(ApproveType::getIsDelete, 0)));
        responseJson.ok();
        return responseJson;
    }

    @Override
    public ResponseJson<String> update(Integer id, JSONObject json) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        ApproveType approveType = JSON.toJavaObject(json, ApproveType.class);
        List<ApproveNode> approveNodeList = approveNodeMapper
                .selectList(new QueryWrapper<ApproveNode>().lambda().eq(ApproveNode::getTypeId, id));
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            baseMapper.update(approveType,
                    new UpdateWrapper<ApproveType>().lambda().eq(ApproveType::getId, id));
            approveType.getApproveNodeList().stream().forEach((ApproveNode approveNode) -> {
                if (ObjectUtil.equal(approveNode.getId(), 0)) {
                    approveNodeMapper.insert(approveNode);
                    return;
                }
                approveNodeMapper.update(approveNode, new UpdateWrapper<ApproveNode>().lambda()
                        .eq(ApproveNode::getId, approveNode.getId()));
            });
            approveNodeList.stream().forEach((ApproveNode approveNode) -> {
                if (!approveType.getApproveNodeList()
                        .stream()
                        .filter(x -> x.getId() > 0)
                        .map(ApproveNode::getId).collect(Collectors.toList())
                        .contains(approveNode.getId())) {
                    approveNodeMapper.delete(new QueryWrapper<ApproveNode>().lambda()
                            .eq(ApproveNode::getId, approveNode.getId()));
                }
            });
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
