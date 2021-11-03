/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-23 16:21:05
 * @LastEditTime: 2021-10-23 16:21:44
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/strategy/impl/EnumGoodsSpecCategoryStrategyImpl.java
 */
package store.tacomall.apiopen.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apiopen.strategy.EnumStrategy;
import store.tacomall.common.entity.goods.GoodsSpecCategory;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.goods.GoodsSpecCategoryMapper;

@Component("goodsSpecCategory")
public class EnumGoodsSpecCategoryStrategyImpl implements EnumStrategy {

    @Autowired
    private GoodsSpecCategoryMapper goodsSpecCategoryMapper;

    @Override
    public ResponseJson<List<Map<String, Object>>> query(JSONObject json) {
        ResponseJson<List<Map<String, Object>>> responseJson = new ResponseJson<>();
        LambdaQueryWrapper<GoodsSpecCategory> lqw = new QueryWrapper<GoodsSpecCategory>().lambda();
        responseJson
                .setData(goodsSpecCategoryMapper.selectList(lqw).stream().map((GoodsSpecCategory goodsSpecCategory) -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", goodsSpecCategory.getId());
                    map.put("name", goodsSpecCategory.getName());
                    return map;
                }).collect(Collectors.toList()));
        responseJson.ok();
        return responseJson;
    }
}