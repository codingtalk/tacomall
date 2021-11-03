/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-27 21:01:48
 * @LastEditTime: 2021-11-01 17:42:12
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/impl/MemberCartServiceImpl.java
 */
package store.tacomall.apima.service.impl;

import org.springframework.core.env.Environment;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hutool.core.util.ObjectUtil;
import store.tacomall.apima.service.MemberCartService;
import store.tacomall.common.entity.member.MemberCart;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.member.MemberCartMapper;

import store.tacomall.common.util.RequestUtil;

@Service
public class MemberCartServiceImpl extends ServiceImpl<MemberCartMapper, MemberCart> implements MemberCartService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    Environment environment;

    @Override
    public ResponseJson<String> add(JSONObject json) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        Integer memberId = RequestUtil.getLoginUser().getInteger("id");
        MemberCart memberCart = JSON.toJavaObject(json, MemberCart.class);
        if (ObjectUtil.isNotNull(baseMapper.selectOne(new QueryWrapper<MemberCart>().lambda()
                .eq(MemberCart::getShopId, 1).eq(MemberCart::getGoodsItemsId, memberCart.getGoodsItemsId())))) {
            baseMapper.update(null, new UpdateWrapper<MemberCart>().lambda()
                    .setSql(String.format("quantity=quantity + %s", memberCart.getQuantity()))
                    .eq(MemberCart::getShopId, 1).eq(MemberCart::getGoodsItemsId, memberCart.getGoodsItemsId()));
            responseJson.setData("添加成功");
            responseJson.ok();
            return responseJson;
        }
        memberCart.setShopId(1);
        memberCart.setMemberId(memberId);
        baseMapper.insert(memberCart);
        responseJson.setData("添加成功");
        responseJson.ok();
        return responseJson;
    }

}
