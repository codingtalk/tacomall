/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2022-01-05 14:01:08
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/shop/src/main/java/store/tacomall/apishop/service/impl/ShopStaffServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apishop.service.impl;

import java.util.HashMap;

import cn.hutool.core.util.ObjectUtil;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import store.tacomall.apishop.service.ShopStaffService;
import store.tacomall.common.annotation.SimpleRestLogin;
import store.tacomall.common.entity.shop.*;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.mapper.shop.ShopStaffMapper;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.common.util.PasswordUtil;
import store.tacomall.common.util.RequestUtil;

@Service()
public class ShopStaffServiceImpl extends ServiceImpl<ShopStaffMapper, ShopStaff> implements ShopStaffService {

    @Autowired
    ShopStaffMapper shopStaffMapper;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Override
    public ResponseJson<String> loginByMobile(JSONObject body) throws Exception {
        ResponseJson<String> responseJson = new ResponseJson<>();
        String mobile = RequestUtil.getStringParam("mobile");
        String passwd = RequestUtil.getStringParam("passwd");
        JwtUtil jwtUtil = new JwtUtil();
        String token = "";
        ShopStaff shopStaff = baseMapper.selectOne(new QueryWrapper<ShopStaff>().lambda()
                .eq(ShopStaff::getMobile, mobile)
                .eq(ShopStaff::getPasswd, PasswordUtil.encode(passwd)));
        if (ObjectUtil.isNull(shopStaff)) {
            responseJson.setMessage("账号或密码错误");
            return responseJson;
        }
        jwtUtil.setISSUER("api-shop");
        token = jwtUtil.create(new HashMap<String, String>() {
            {
                put("id", shopStaff.getId().toString());
            }
        });
        responseJson.setData(token);
        responseJson.ok();
        return responseJson;
    }

    @Override
    @SimpleRestLogin
    public ResponseJson<ShopStaff> info(JSONObject body) {
        Integer id = RequestUtil.getIntegerParam("id");
        ResponseJson<ShopStaff> responseJson = new ResponseJson<>();
        responseJson.setData(baseMapper.selectById(id.equals(0) ? RequestUtil
                .getLoginUser()
                .getInteger("id")
                : id));
        responseJson.ok();
        return responseJson;
    }

}
