/***
 * @Author: 码上talk|RC
 * @Date: 2020-12-15 15:16:04
 * @LastEditTime: 2021-10-23 16:19:20
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/strategy/impl/EnumSysRegionStrategyImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiopen.strategy.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import store.tacomall.apiopen.strategy.EnumStrategy;
import store.tacomall.common.entity.sys.SysRegion;
import store.tacomall.common.mapper.sys.SysRegionMapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.common.json.ResponseJson;

@Component("sysRegion")
public class EnumSysRegionStrategyImpl implements EnumStrategy {

    @Autowired
    SysRegionMapper sysRegionMapper;

    @Override
    public ResponseJson<List<Map<String, Object>>> query(JSONObject json) {
        ResponseJson<List<Map<String, Object>>> responseJson = new ResponseJson<>();
        LambdaQueryWrapper<SysRegion> lqw = new QueryWrapper<SysRegion>().lambda();
        JSONObject query = json.getJSONObject("query");
        boolean checkHasChildren = false;
        if (ObjectUtil.isNotNull(query)) {
            if (ObjectUtil.isNotEmpty(query.get("pId"))) {
                lqw.eq(SysRegion::getPId, query.getInteger("pId"));
            }
            if (ObjectUtil.isNotEmpty(query.get("checkHasChildren"))) {
                checkHasChildren = query.getBoolean("checkHasChildren");
            }
        }
        boolean finalCheckHasChildren = checkHasChildren;
        responseJson.setData(sysRegionMapper.selectList(lqw).stream().map((SysRegion sysRegion) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", sysRegion.getId());
            map.put("pId", sysRegion.getPId());
            map.put("name", sysRegion.getName());
            map.put("lat", sysRegion.getLat());
            map.put("lng", sysRegion.getLng());
            map.put("level", sysRegion.getLevel());
            if (finalCheckHasChildren) {
                map.put("isHasChildren",
                        sysRegionMapper.selectCount(new QueryWrapper<SysRegion>().lambda()
                                .eq(SysRegion::getPId, sysRegion.getId()).eq(SysRegion::getIsDelete, 0)) > 0 ? true
                                        : false);
            }
            return map;
        }).collect(Collectors.toList()));
        responseJson.ok();
        return responseJson;
    }

}
