/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-18 15:01:19
 * @LastEditTime: 2021-10-22 14:05:55
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/impl/TmJobServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import store.tacomall.apiadmin.service.TmJobService;
import store.tacomall.common.entity.tm.TmJob;
import store.tacomall.common.enumeration.BizEnum;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.tm.TmAccessRuleMapper;
import store.tacomall.common.mapper.tm.TmJobMapper;
import store.tacomall.common.util.ExceptionUtil;

@Service
public class TmJobServiceImpl extends ServiceImpl<TmJobMapper, TmJob> implements TmJobService {

  @Autowired
  TmAccessRuleMapper tmAccessRuleMapper;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponsePageJson<List<TmJob>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<TmJob>> responsePageJson = new ResponsePageJson<>();
    Page<TmJob> page = new Page<>(pageIndex, pageSize);
    LambdaQueryWrapper<TmJob> q = new QueryWrapper<TmJob>().lambda();
    if (ObjectUtil.isNotEmpty(json.getJSONObject("query"))
        && ObjectUtil.isNotEmpty(json.getJSONObject("query").get("keyword"))) {
      q.like(TmJob::getName, json.getJSONObject("query").get("keyword"));
    }
    q.eq(TmJob::getIsDelete, 0);
    IPage<TmJob> result = baseMapper.selectPage(page, q);
    responsePageJson.setData(result.getRecords());
    responsePageJson.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePageJson.ok();
    return responsePageJson;
  }

  @Override
  public ResponseJson<TmJob> info(Integer id) {
    ResponseJson<TmJob> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper.selectOne(new QueryWrapper<TmJob>().lambda().eq(TmJob::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<TmJob> add(JSONObject json) {
    ResponseJson<TmJob> responseJson = new ResponseJson<>();
    responseJson.setStatus(false);
    TmJob tmJob = JSON.toJavaObject(json, TmJob.class);
    baseMapper.insert(tmJob);
    responseJson.setData(tmJob);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    TmJob tmJob = JSON.toJavaObject(json, TmJob.class);
    baseMapper.updateById(tmJob);
    responseJson.setData("更新成功");
    responseJson.ok();
    return responseJson;
  }

}
