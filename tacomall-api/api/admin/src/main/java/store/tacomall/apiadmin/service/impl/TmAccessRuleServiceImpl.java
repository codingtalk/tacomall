/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-18 15:01:39
 * @LastEditTime: 2021-10-22 14:03:07
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/impl/TmAccessRuleServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.stereotype.Service;

import store.tacomall.apiadmin.service.TmAccessRuleService;
import store.tacomall.common.entity.tm.TmAccessRule;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.tm.TmAccessRuleMapper;

@Service
public class TmAccessRuleServiceImpl extends ServiceImpl<TmAccessRuleMapper, TmAccessRule>
    implements TmAccessRuleService {

  @Override
  public ResponsePageJson<List<TmAccessRule>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<TmAccessRule>> responsePageVo = new ResponsePageJson<>();
    Page<TmAccessRule> page = new Page<>(pageIndex, pageSize);
    LambdaQueryWrapper<TmAccessRule> q = new QueryWrapper<TmAccessRule>().lambda();
    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("keyword"))) {
      q.like(TmAccessRule::getName, query.get("keyword"));
    }
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("pId"))) {
      q.in(TmAccessRule::getPId, Arrays.asList(query.getString("pId").split(",")));
    }
    q.like(TmAccessRule::getIsDelete, 0);
    IPage<TmAccessRule> result = baseMapper.selectPage(page, q);
    responsePageVo.setData(result.getRecords());
    responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePageVo.ok();
    return responsePageVo;
  }

  @Override
  public ResponseJson<TmAccessRule> info(Integer id) {
    ResponseJson<TmAccessRule> responseJson = new ResponseJson<>();
    responseJson
        .setData(baseMapper.selectOne(new QueryWrapper<TmAccessRule>().lambda().eq(TmAccessRule::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<TmAccessRule> add(JSONObject jsonObject) {
    ResponseJson<TmAccessRule> responseJson = new ResponseJson<>();
    TmAccessRule tmAccessRule = JSON.toJavaObject(jsonObject, TmAccessRule.class);
    baseMapper.insert(tmAccessRule);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    TmAccessRule tmAccessRule = JSON.toJavaObject(json, TmAccessRule.class);
    baseMapper.updateById(tmAccessRule);
    responseJson.setData("更新成功");
    responseJson.ok();
    return responseJson;
  }

}
