/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-11 22:02:56
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/shop/src/main/java/store/tacomall/apishop/service/impl/ShopStaffServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apishop.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;

import store.tacomall.apishop.service.ShopStaffService;
import store.tacomall.common.entity.member.Member;
import store.tacomall.common.entity.shop.*;
import store.tacomall.common.enumeration.BizEnum;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.member.MemberMapper;
import store.tacomall.common.mapper.shop.ShopStaffMapper;
import store.tacomall.common.util.ExceptionUtil;
import store.tacomall.common.util.JwtUtil;
import store.tacomall.common.util.PasswordUtil;
import store.tacomall.common.util.RequestUtil;
import store.tacomall.common.vo.base.PageVo;

@Service
public class ShopStaffServiceImpl extends ServiceImpl<ShopStaffMapper, ShopStaff> implements ShopStaffService {

  @Autowired
  ShopStaffMapper shopStaffMapper;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Override
  public ResponseJson<String> loginByMobile(String mobile, String password) throws Exception {
    ResponseJson<String> responseJson = new ResponseJson<>();
    String token = "";
    LambdaQueryWrapper<ShopStaff> lqw = new QueryWrapper<ShopStaff>().lambda();
    lqw.eq(ShopStaff::getMobile, mobile);
    lqw.eq(ShopStaff::getPasswd, PasswordUtil.encode(password));

    ShopStaff shopStaff = baseMapper.selectOne(lqw);
    if (ObjectUtil.isNull(shopStaff)) {
      responseJson.setMessage("账号或密码错误");
      responseJson.setCode(BizEnum.USER_NOT_EXIST.getCode());
      return responseJson;
    }

    Map<String, String> claims = new HashMap<>(1);
    claims.put("id", NumberUtil.toStr(shopStaff.getId()));
    JwtUtil jwtUtil = new JwtUtil();
    jwtUtil.setISSUER("api-shop");
    token = jwtUtil.create(claims);
    responseJson.setData(token);
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<ShopStaff> info(JSONObject json) {
    ResponseJson<ShopStaff> responseJson = new ResponseJson<>();
    ShopStaff shopStaff = shopStaffMapper.selectById(RequestUtil.getLoginUser().getString("id"));
    JSONObject query = json.getJSONObject("query");
    LambdaQueryWrapper<ShopStaff> qw = new LambdaQueryWrapper<ShopStaff>();

    if (ObjectUtil.isNotNull(query) && ObjectUtil.isNotNull(query.getInteger("id"))) {
      qw.eq(ShopStaff::getId, query.getInteger("id"));
    } else {
      qw.eq(ShopStaff::getId, shopStaff.getId());
    }

    responseJson.setData(this.baseMapper.selectOne(qw));
    responseJson.ok();
    return responseJson;
  }

}
