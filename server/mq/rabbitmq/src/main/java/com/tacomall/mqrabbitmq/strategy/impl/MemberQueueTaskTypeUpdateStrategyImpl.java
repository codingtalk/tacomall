package com.tacomall.mqrabbitmq.strategy.impl;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import com.tacomall.common.dto.MemberQueueTaskDto;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.mapper.member.MemberMapper;
import com.tacomall.mqrabbitmq.strategy.MemberQueueTaskStrategy;

@Component("TypeUpdate")
public class MemberQueueTaskTypeUpdateStrategyImpl extends ServiceImpl<MemberMapper, Member>
        implements MemberQueueTaskStrategy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Override
    public Boolean doTask(MemberQueueTaskDto memberQueueTaskDto) {

        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(transactionDefinition);
        try {

          dataSourceTransactionManager.commit(transactionStatus);
        } catch (

        Exception e) {
            dataSourceTransactionManager.rollback(transactionStatus);
            logger.error(e.toString());
        }
        return true;
    }

}