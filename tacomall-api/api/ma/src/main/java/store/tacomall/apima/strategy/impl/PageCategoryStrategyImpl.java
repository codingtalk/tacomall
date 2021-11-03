/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-13 11:06:56
 * @LastEditTime: 2021-10-27 14:07:53
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/strategy/impl/PageCategoryStrategyImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.strategy.impl;

import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apima.strategy.PageStrategy;
import store.tacomall.apima.vo.page.PageGoodsCategoryVo;
import store.tacomall.common.entity.goods.GoodsCategory;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.goods.GoodsCategoryMapper;

@Component("category")
public class PageCategoryStrategyImpl implements PageStrategy {

  @Autowired
  private GoodsCategoryMapper goodsCategoryMapper;

  @Override
  public ResponseJson<Object> loadPageInfo(JSONObject json) {
    ResponseJson<Object> responseJson = new ResponseJson<>();
    responseJson.setData(PageGoodsCategoryVo.builder()
        .goodsCategoryList(
            goodsCategoryMapper.selectList(new QueryWrapper<GoodsCategory>().lambda().eq(GoodsCategory::getPId, 0))
                .stream().map((GoodsCategory i) -> {
                  i.setSub(goodsCategoryMapper
                      .selectList(new QueryWrapper<GoodsCategory>().lambda().eq(GoodsCategory::getPId, i.getId()))
                      .stream().map((GoodsCategory j) -> {
                        j.setSub(goodsCategoryMapper
                            .selectList(new QueryWrapper<GoodsCategory>().lambda().eq(GoodsCategory::getPId, j.getId()))
                            .stream().collect(Collectors.toList()));
                        return j;
                      }).collect(Collectors.toList()));
                  return i;
                }).collect(Collectors.toList()))
        .build());
    responseJson.ok();
    return responseJson;
  }
}
