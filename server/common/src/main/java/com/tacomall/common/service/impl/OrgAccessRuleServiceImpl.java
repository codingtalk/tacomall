package com.tacomall.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.common.entity.org.OrgAccessRule;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.mapper.org.OrgAccessRuleMapper;
import com.tacomall.common.service.OrgAccessRuleService;

import org.springframework.stereotype.Service;

@Service
public class OrgAccessRuleServiceImpl extends ServiceImpl<OrgAccessRuleMapper, OrgAccessRule>
    implements OrgAccessRuleService {

  @Override
  public ResponseJson<List<OrgAccessRule>> list(JSONObject json) {
    ResponseJson<List<OrgAccessRule>> responseJson = new ResponseJson<>();
    JSONObject query = json.getJSONObject("query");
    LambdaQueryWrapper<OrgAccessRule> lqw = new QueryWrapper<OrgAccessRule>().lambda();
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("ids"))) {
      List<String> queryIds = new ArrayList<>();
      List<String> uniqueIds = new ArrayList<>();
      baseMapper.selectList(new QueryWrapper<OrgAccessRule>().lambda().in(OrgAccessRule::getId,
          Arrays.asList(query.getString("ids").split(",")))).stream().forEach(i -> {
            Arrays.asList(i.getChainIds().split("<")).stream().forEach(j -> {
              queryIds.add(j);
            });
            queryIds.add(String.valueOf(i.getId()));
          });
      uniqueIds = queryIds.stream().distinct().collect(Collectors.toList());
      lqw.in(OrgAccessRule::getId, uniqueIds);
    }
    lqw.eq(OrgAccessRule::getIsDelete, 0);
    responseJson.setData(baseMapper.selectList(lqw));
    responseJson.ok();
    return responseJson;
  }

}