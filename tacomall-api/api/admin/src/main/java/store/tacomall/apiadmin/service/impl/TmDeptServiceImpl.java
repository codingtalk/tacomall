/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-22 14:03:35
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/impl/TmDeptServiceImpl.java
 * @Just do what I think it is right
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

import store.tacomall.apiadmin.service.TmDeptService;
import store.tacomall.common.entity.tm.TmDept;
import store.tacomall.common.enumeration.BizEnum;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.tm.TmDeptMapper;
import store.tacomall.common.util.ExceptionUtil;

@Service
public class TmDeptServiceImpl extends ServiceImpl<TmDeptMapper, TmDept> implements TmDeptService {

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponsePageJson<List<TmDept>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<TmDept>> responsePageVo = new ResponsePageJson<>();
    Page<TmDept> page = new Page<>(pageIndex, pageSize);
    LambdaQueryWrapper<TmDept> q = new QueryWrapper<TmDept>().lambda();
    if (ObjectUtil.isNotEmpty(json.getJSONObject("query"))
        && ObjectUtil.isNotEmpty(json.getJSONObject("query").get("keyword"))) {
      q.like(TmDept::getName, json.getJSONObject("query").get("keyword"));
    }
    q.eq(TmDept::getIsDelete, 0);
    IPage<TmDept> result = baseMapper.selectPage(page, q);
    responsePageVo.setData(result.getRecords());
    responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePageVo.ok();
    return responsePageVo;
  }

  @Override
  public ResponseJson<TmDept> info(Integer id) {
    ResponseJson<TmDept> responseJson = new ResponseJson<>();
    responseJson.setData(baseMapper.selectOne(new QueryWrapper<TmDept>().lambda().eq(TmDept::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<TmDept> add(JSONObject json) {
    ResponseJson<TmDept> responseJson = new ResponseJson<>();
    TmDept tmDept = JSON.toJavaObject(json, TmDept.class);
    baseMapper.insert(tmDept);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    TmDept tmDept = JSON.toJavaObject(json, TmDept.class);
    baseMapper.updateById(tmDept);
    responseJson.setData("更新成功");
    responseJson.ok();
    return responseJson;
  }

}
