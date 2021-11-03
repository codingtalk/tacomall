/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-23 16:15:38
 * @LastEditTime: 2021-10-23 16:20:04
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/strategy/impl/EnumGoodsCategoryStrategyImpl.java
 */
package store.tacomall.apiopen.strategy.impl;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.hutool.core.util.ObjectUtil;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.apiopen.strategy.EnumStrategy;
import store.tacomall.common.entity.goods.GoodsCategory;
import store.tacomall.common.mapper.goods.GoodsCategoryMapper;

@Component("goodsCategory")
public class EnumGoodsCategoryStrategyImpl implements EnumStrategy {

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;

    @Override
    public ResponseJson<List<Map<String, Object>>> query(JSONObject json) {
        ResponseJson<List<Map<String, Object>>> responseJson = new ResponseJson<>();
        LambdaQueryWrapper<GoodsCategory> lqw = new QueryWrapper<GoodsCategory>().lambda();
        JSONObject query = json.getJSONObject("query");
        if (ObjectUtil.isNotNull(query)) {
            if (ObjectUtil.isNotEmpty(query.get("pId"))) {
                lqw.eq(GoodsCategory::getPId, query.getInteger("pId"));
            }
        }
        responseJson.setData(goodsCategoryMapper.selectList(lqw).stream().map((GoodsCategory goodsCategory) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", goodsCategory.getId());
            map.put("name", goodsCategory.getName());
            map.put("isHasChildren",
                    goodsCategoryMapper.selectList(
                            new QueryWrapper<GoodsCategory>().lambda().eq(GoodsCategory::getPId, goodsCategory.getId()))
                            .size() > 0 ? true : false);
            return map;
        }).collect(Collectors.toList()));
        responseJson.ok();
        return responseJson;
    }
}
