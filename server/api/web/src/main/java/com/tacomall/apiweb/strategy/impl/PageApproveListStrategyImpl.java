package com.tacomall.apiweb.strategy.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tacomall.apiweb.strategy.PageStrategy;
import com.tacomall.apiweb.vo.page.PageApproveListVo;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.approve.ApproveTypeMapper;

@Component("approveList")
public class PageApproveListStrategyImpl implements PageStrategy {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ApproveTypeMapper approveTypeMapper;

    @Override
    public ResponseJson<Object> info(JSONObject json) {
        ResponseJson<Object> responseJson = new ResponseJson<>();
        responseJson.setData(PageApproveListVo.builder()
                .approveTypeList(approveTypeMapper.selectList(null))
                .build());
        responseJson.ok();
        return responseJson;
    }
}
