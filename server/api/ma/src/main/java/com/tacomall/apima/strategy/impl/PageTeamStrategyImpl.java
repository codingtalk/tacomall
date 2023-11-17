
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
import com.tacomall.apima.vo.page.PageTeamVo;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.entity.member.MemberWallet;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.member.MemberMapper;
import com.tacomall.common.util.RequestUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;

@Component("team")
public class PageTeamStrategyImpl implements PageStrategy {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  MemberMapper memberMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<PageTeamVo> info(JSONObject body) {
    ResponseJson<PageTeamVo> responseJson = new ResponseJson<>();
    Integer memberId = RequestUtil.getLoginUser().getInteger("id");
    responseJson.setData(PageTeamVo.builder()
        .countInvite(memberMapper.selectCount(new QueryWrapper<Member>()
            .lambda()
            .eq(Member::getInviteMemberId, memberId)))
        .countSubInvite(memberMapper.selectCount(new QueryWrapper<Member>()
            .lambda()
            .eq(Member::getInviteUpperMemberId, memberId)))
        .build());
    responseJson.ok();
    return responseJson;
  }
}
