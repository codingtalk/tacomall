/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-05 16:33:00
 * @LastEditTime: 2021-11-05 16:49:54
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/strategy/impl/PageSearchStrategyImpl.java
 */
package store.tacomall.apima.strategy.impl;

import java.util.Arrays;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apima.strategy.PageStrategy;
import store.tacomall.apima.vo.page.PageSearchVo;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.goods.GoodsCategoryMapper;
import store.tacomall.common.mapper.goods.GoodsMapper;
import store.tacomall.common.mapper.ma.MaCarouselMapper;

@Component("search")
public class PageSearchStrategyImpl implements PageStrategy {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  MaCarouselMapper maCarouselMapper;

  @Autowired
  GoodsCategoryMapper goodsCategoryMapper;

  @Autowired
  GoodsMapper goodsMapper;

  @Override
  public ResponseJson<Object> loadPageInfo(JSONObject json) {
    ResponseJson<Object> responseJson = new ResponseJson<>();
    responseJson.setData(PageSearchVo.builder().keyword(Arrays.asList("冬季保暖", "男士内裤"))
        .hot(goodsMapper
            .selectList(new QueryWrapper<Goods>().lambda().orderByDesc(Goods::getCreateTime).last("limit 5")))
        .favour(goodsMapper
            .selectList(new QueryWrapper<Goods>().lambda().orderByDesc(Goods::getCreateTime).last("limit 5")))
        .build());
    responseJson.ok();
    return responseJson;
  }
}
