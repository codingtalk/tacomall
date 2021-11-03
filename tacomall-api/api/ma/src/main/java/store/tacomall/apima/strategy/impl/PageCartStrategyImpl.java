/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-26 17:22:00
 * @LastEditTime: 2021-11-02 16:17:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/strategy/impl/PageCartStrategyImpl.java
 */
package store.tacomall.apima.strategy.impl;

import java.util.Optional;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import store.tacomall.apima.strategy.PageStrategy;
import store.tacomall.apima.vo.page.PageCartVo;
import store.tacomall.common.constant.DataResultConstant;
import store.tacomall.common.entity.member.MemberCart;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.member.MemberCartMapper;
import store.tacomall.common.util.RequestUtil;

@Component("cart")
public class PageCartStrategyImpl implements PageStrategy {

    @Autowired
    private MemberCartMapper memberCartMapper;

    @SuppressWarnings("unchecked")
    @Override
    public ResponseJson<Object> loadPageInfo(JSONObject json) {
        ResponseJson<Object> responseJson = new ResponseJson<>();
        responseJson.setData(PageCartVo.builder().cartList(Optional.ofNullable(RequestUtil.getLoginUser()).map(j -> {
            QueryWrapper<MemberCart> qw = new QueryWrapper<MemberCart>();
            qw.eq("mc.member_id", j.getInteger("id"));
            qw.eq("mc.status", 1);
            qw.orderByDesc("mc.create_time");
            return memberCartMapper.queryList(qw);
        }).orElse(DataResultConstant.EMPTY_LIST)).build());
        responseJson.ok();
        return responseJson;
    }
}