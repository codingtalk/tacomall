
package com.tacomall.apima.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tacomall.apima.strategy.PageStrategy;
import com.tacomall.apima.vo.page.PageFormOrderAddVo;
import com.tacomall.common.entity.logistic.LogisticType;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.logistic.LogisticTypeMapper;
import com.tacomall.common.util.ExceptionUtil;
import com.tacomall.common.util.SnUtil;

import cn.hutool.core.util.ObjectUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;

@Component("orderFormAdd")
public class PageFormOrderAddStrategyImpl implements PageStrategy {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  LogisticTypeMapper logisticTypeMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<PageFormOrderAddVo> info(JSONObject body) {
    ResponseJson<PageFormOrderAddVo> responseJson = new ResponseJson<>();
    String type = body.getString("type");
    BigDecimal amountActual = BigDecimal.ZERO;

    // @TODO calculate the amount

    responseJson.setData(PageFormOrderAddVo.builder()
        .amountActual(amountActual.setScale(2, BigDecimal.ROUND_HALF_UP)).build());
    responseJson.ok();
    return responseJson;
  }
}
