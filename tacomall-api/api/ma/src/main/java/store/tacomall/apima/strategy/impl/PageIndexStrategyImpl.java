/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-10 17:00:09
 * @LastEditTime: 2021-10-27 10:48:42
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/strategy/impl/PageIndexStrategyImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.strategy.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apima.strategy.PageStrategy;
import store.tacomall.apima.vo.page.PageIndexVo;
import store.tacomall.apima.vo.page.PageIndexVo.PageIndexVoBuilder;
import store.tacomall.common.entity.goods.Goods;
import store.tacomall.common.entity.goods.GoodsCategory;
import store.tacomall.common.entity.ma.MaCarousel;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.goods.GoodsCategoryMapper;
import store.tacomall.common.mapper.goods.GoodsMapper;
import store.tacomall.common.mapper.ma.MaCarouselMapper;

@Component("index")
public class PageIndexStrategyImpl implements PageStrategy {

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
        PageIndexVoBuilder pageIndexVoBuilder = PageIndexVo.builder();
        List<Map<String, String>> sectionK = Arrays.asList(new HashMap<String, String>() {
            {
                put("k", "HOT");
                put("n", "热门推荐");
            }
        }, new HashMap<String, String>() {
            {
                put("k", "HUAN_JI");
                put("n", "换季冬装");
            }
        });
        List<Map<String, Object>> s = new ArrayList<>();
        sectionK.stream().forEach(k -> {
            if (k.get("k").equals("HOT")) {
                s.add(new HashMap<String, Object>() {
                    {
                        put("k", k.get("k"));
                        put("n", k.get("n"));
                        put("g", goodsMapper.selectList(new QueryWrapper<Goods>().lambda().last("limit 6")));
                    }
                });
            } else if (k.get("k").equals("HUAN_JI")) {
                s.add(new HashMap<String, Object>() {
                    {
                        put("k", k.get("k"));
                        put("n", k.get("n"));
                        put("g", goodsMapper.selectList(new QueryWrapper<Goods>().lambda().last("limit 6")));
                    }
                });
            }
        });
        pageIndexVoBuilder.sections(s);
        responseJson.setData(pageIndexVoBuilder
                .maCarouselList(maCarouselMapper.selectList(
                        new QueryWrapper<MaCarousel>().lambda().orderByDesc(MaCarousel::getPriority).last("limit 5")))
                .goodsCategoryList(
                        goodsCategoryMapper.selectList(new QueryWrapper<GoodsCategory>().lambda().last("limit 10")))
                .build());
        responseJson.ok();
        return responseJson;
    }
}
