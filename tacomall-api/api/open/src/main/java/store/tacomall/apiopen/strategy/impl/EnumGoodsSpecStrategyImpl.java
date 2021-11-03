/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-23 16:29:22
 * @LastEditTime: 2021-10-23 16:34:12
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/strategy/impl/EnumGoodsSpecStrategyImpl.java
 */
package store.tacomall.apiopen.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apiopen.strategy.EnumStrategy;
import store.tacomall.common.entity.goods.GoodsSpecKey;
import store.tacomall.common.entity.goods.GoodsSpecValue;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.goods.GoodsSpecKeyMapper;
import store.tacomall.common.mapper.goods.GoodsSpecValueMapper;

@Component("goodsSpec")
public class EnumGoodsSpecStrategyImpl implements EnumStrategy {

  @Autowired
  private GoodsSpecKeyMapper goodsSpecKeyMapper;

  @Autowired
  private GoodsSpecValueMapper goodsSpecValueMapper;

  @Override
  public ResponseJson<List<Map<String, Object>>> query(JSONObject json) {
    ResponseJson<List<Map<String, Object>>> responseJson = new ResponseJson<>();
    JSONObject query = json.getJSONObject("query");
    LambdaQueryWrapper<GoodsSpecKey> lqw = new QueryWrapper<GoodsSpecKey>().lambda();
    if (ObjectUtil.isNotNull(query.get("specCategoryId"))) {
      lqw.eq(GoodsSpecKey::getSpecCategoryId, query.get("specCategoryId"));
    }
    lqw.eq(GoodsSpecKey::getIsDelete, 0);
    responseJson.setData(goodsSpecKeyMapper.selectList(lqw).stream().map((GoodsSpecKey goodsSpecKey) -> {
      Map<String, Object> mapK = new HashMap<>();
      mapK.put("id", goodsSpecKey.getId());
      mapK.put("name", goodsSpecKey.getName());
      mapK.put("children", goodsSpecValueMapper
          .selectList(new QueryWrapper<GoodsSpecValue>().lambda().eq(GoodsSpecValue::getKeyId, goodsSpecKey.getId()))
          .stream().map((GoodsSpecValue goodsSpecValue) -> {
            Map<String, Object> mapV = new HashMap<>();
            mapV.put("id", goodsSpecValue.getId());
            mapV.put("name", goodsSpecValue.getName());
            return mapV;
          }).collect(Collectors.toList()));
      return mapK;
    }).collect(Collectors.toList()));
    responseJson.ok();
    return responseJson;
  }
}
