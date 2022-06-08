/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-18 14:28:29
 * @LastEditTime: 2021-10-23 10:06:55
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/impl/GoodsCategoryServiceImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import store.tacomall.apiadmin.service.GoodsCategoryService;
import store.tacomall.common.entity.goods.GoodsCategory;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.goods.GoodsCategoryMapper;

@Service
public class GoodsCategoryServiceImpl extends ServiceImpl<GoodsCategoryMapper, GoodsCategory>
    implements GoodsCategoryService {

  @Override
  public ResponsePageJson<List<GoodsCategory>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<GoodsCategory>> responsePageJson = new ResponsePageJson<>();
    Page<GoodsCategory> page = new Page<>(pageIndex, pageSize);
    LambdaQueryWrapper<GoodsCategory> lqw = new QueryWrapper<GoodsCategory>().lambda();
    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("keyword"))) {
      lqw.like(GoodsCategory::getName, query.get("keyword"));
    }
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("pId"))) {
      lqw.in(GoodsCategory::getPId, Arrays.asList(query.getString("pId").split(",")));
    }
    lqw.eq(GoodsCategory::getIsDelete, 0);
    IPage<GoodsCategory> result = baseMapper.selectPage(page, lqw);
    responsePageJson.setData(result.getRecords());
    responsePageJson.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePageJson.ok();
    return responsePageJson;
  }

  @Override
  public ResponseJson<GoodsCategory> add(JSONObject json) {
    ResponseJson<GoodsCategory> responseJson = new ResponseJson<>();
    GoodsCategory goodsCategory = JSON.toJavaObject(json, GoodsCategory.class);
    baseMapper.insert(goodsCategory);
    responseJson.setData(goodsCategory);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<GoodsCategory> info(Integer id) {
    ResponseJson<GoodsCategory> responseJson = new ResponseJson<>();
    responseJson
        .setData(baseMapper.selectOne(new QueryWrapper<GoodsCategory>().lambda().eq(GoodsCategory::getId, id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    GoodsCategory goodsCategory = JSON.toJavaObject(json, GoodsCategory.class);
    baseMapper.updateById(goodsCategory);
    responseJson.setData("更新成功");
    responseJson.ok();
    return responseJson;
  }

}
