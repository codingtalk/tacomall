
package com.tacomall.apima.strategy.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tacomall.apima.strategy.PageStrategy;
import com.tacomall.apima.vo.page.PageIndexVo;
import com.tacomall.apima.vo.page.PageMeVo;
import com.tacomall.common.entity.member.MemberWallet;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.member.MemberWalletMapper;
import com.tacomall.common.util.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;

@Component("me")
public class PageMeStrategyImpl implements PageStrategy {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  MemberWalletMapper walletMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<PageMeVo> info(JSONObject body) {
    ResponseJson<PageMeVo> responseJson = new ResponseJson<>();
    responseJson.setData(PageMeVo.builder()
        .wallet(walletMapper.selectOne(new QueryWrapper<MemberWallet>()
            .lambda()
            .eq(MemberWallet::getMemberId, RequestUtil.getLoginUser().getInteger("id"))))
        .build());
    responseJson.ok();
    return responseJson;
  }
}
