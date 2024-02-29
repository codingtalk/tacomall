
package com.tacomall.apiadmin.strategy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;

import com.tacomall.apiadmin.strategy.ApproveStrategy;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.service.ApproveService;

@Component("index")
public class ApproveIndexStrategyImpl implements ApproveStrategy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApproveService approveService;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    public ResponseJson<Object> doAgree(Integer id, Integer isAgree,
            JSONObject json) {
        ResponseJson<Object> responseJson = new ResponseJson<>();
        return responseJson;
    }

    @Override
    public ResponseJson<Object> cancel(Integer id, JSONObject json) {
        ResponseJson<Object> responseJson = new ResponseJson<>();
        return responseJson;
    }
}
