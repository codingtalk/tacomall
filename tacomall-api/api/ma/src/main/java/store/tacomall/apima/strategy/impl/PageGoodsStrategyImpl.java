/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-13 11:06:56
 * @LastEditTime: 2021-10-27 10:27:47
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/strategy/impl/PageGoodsStrategyImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.strategy.impl;

import java.util.Optional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apima.strategy.PageStrategy;
import store.tacomall.apima.vo.page.PageGoodsVo;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.goods.GoodsMapper;

@Component("goods")
public class PageGoodsStrategyImpl implements PageStrategy {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public ResponseJson<Object> loadPageInfo(JSONObject json) {
        ResponseJson<Object> responseJson = new ResponseJson<>();
        responseJson.setData(PageGoodsVo.builder()
                .goods(goodsMapper.queryInfo(new QueryWrapper<Goods>().lambda().eq(Goods::getId,
                        Optional.ofNullable(json.getInteger("id")).orElseThrow(() -> new RuntimeException("参数有误~")))))
                .build());
        responseJson.ok();
        return responseJson;
    }
}
