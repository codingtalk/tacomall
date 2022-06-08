/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-12-02 11:10:37
 * @LastEditTime: 2021-12-28 14:00:16
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /server-api/common/src/main/java/com/tszl/common/logic/approve/impl/ApproveLogicImpl.java
 */
package store.tacomall.common.logic.approve.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import cn.hutool.core.util.ObjectUtil;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.logic.approve.ApproveLogic;
import store.tacomall.common.mapper.approve.ApproveMapper;
import store.tacomall.common.mapper.approve.ApproveNodeMapper;
import store.tacomall.common.mapper.approve.ApproveProcessMapper;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.RequestUtil;
import store.tacomall.common.constant.approve.ApproveConstant;
import store.tacomall.common.entity.approve.Approve;
import store.tacomall.common.entity.approve.ApproveNode;
import store.tacomall.common.entity.approve.ApproveProcess;

@Service
public class ApproveLogicImpl extends ServiceImpl<ApproveMapper, Approve> implements ApproveLogic {

    @Autowired
    Environment environment;

    @Autowired
    ApproveMapper approveMapper;

    @Autowired
    ApproveNodeMapper approveNodeMapper;

    @Autowired
    ApproveProcessMapper approveProcessMapper;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public ResponseJson<String> doAgree(Integer id, Integer isAgree, JSONObject json) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        Approve approve = baseMapper.selectById(id);
        List<ApproveNode> approveNodeList = approveNodeMapper
                .selectList(new QueryWrapper<ApproveNode>().lambda().eq(ApproveNode::getTypeId, approve.getTypeId()));
        Integer nextStatus = 1;
        Integer nextNodeId = null;
        if (ObjectUtil.isNull(approve) || !approve.getStatus().equals(ApproveConstant.STATUS_CHECKING)) {
            ExceptionUtil.throwServerException("审核不存在");
        }
        if (approveNodeList.size() == 0) {
            ExceptionUtil.throwServerException("审核未设置节点");
        }
        Integer approveNodeIdx = Arrays.binarySearch(approveNodeList.stream().map(i -> i.getId()).toArray(),
                approve.getNodeId());
        if (isAgree.equals(1)) {
            if (approveNodeList.size() - 1 == approveNodeIdx) {
                nextStatus = ApproveConstant.STATUS_OK;
            } else {
                nextNodeId = approveNodeList.get(approveNodeIdx + 1).getId();
            }
        } else {
            if (approveNodeIdx.equals(0)) {
                nextStatus = ApproveConstant.STATUS_ERROR;
            } else {
                nextNodeId = approveNodeList.get(approveNodeIdx - 1).getId();
            }
        }
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            approveProcessMapper.insert(
                    ApproveProcess.builder().approveId(id).nodeId(approve.getNodeId())
                            .checkerId(Optional.ofNullable(RequestUtil.getLoginUser()).flatMap(x -> {
                                return Optional.of(x.getInteger("id"));
                            }).orElse(1)).status(isAgree).build());
            baseMapper.update(null, new UpdateWrapper<Approve>().lambda().eq(Approve::getId, id)
                    .set(Approve::getStatus, nextStatus).set(Approve::getNodeId, nextNodeId));
            dataSourceTransactionManager.commit(transactionStatus);
            responseJson.ok();
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
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {
            approveProcessMapper.insert(
                    ApproveProcess.builder().approveId(id).nodeId(approve.getNodeId())
                            .checkerId(Optional.ofNullable(RequestUtil.getLoginUser()).flatMap(x -> {
                                return Optional.of(x.getInteger("id"));
                            }).orElse(1)).status(ApproveConstant.STATUS_CANCEL).build());
            baseMapper.update(null, new UpdateWrapper<Approve>().lambda().eq(Approve::getId, id)
                    .set(Approve::getStatus, ApproveConstant.STATUS_CANCEL).set(Approve::getNodeId, null));
            dataSourceTransactionManager.commit(transactionStatus);
            responseJson.ok();
        } catch (Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            ExceptionUtil.throwServerException(e.toString());
        }
        return responseJson;
    }

}