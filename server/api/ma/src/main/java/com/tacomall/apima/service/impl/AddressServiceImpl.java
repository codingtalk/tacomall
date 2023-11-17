package com.tacomall.apima.service.impl;

import java.util.List;

import cn.hutool.core.util.ObjectUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import com.tacomall.apima.dto.AddressUpdateDto;
import com.tacomall.apima.dto.MemberUpdateDto;
import com.tacomall.apima.service.AddressService;
import com.tacomall.common.annotation.SimpleRestLogin;
import com.tacomall.common.entity.member.MemberAddress;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.member.MemberAddressMapper;
import com.tacomall.common.util.RequestUtil;
import com.tacomall.common.vo.base.PaginatorVo;

@Service
public class AddressServiceImpl extends ServiceImpl<MemberAddressMapper, MemberAddress> implements AddressService {

    @Autowired
    Environment environment;

    @Autowired
    TransactionDefinition transactionDefinition;

    @Autowired
    DataSourceTransactionManager dataSourceTransactionManager;

    @Override
    @SimpleRestLogin
    public ResponseJson<MemberAddress> add(JSONObject json) {
        ResponseJson<MemberAddress> responseJson = new ResponseJson<>();
        MemberAddress address = JSON.toJavaObject(json, MemberAddress.class);
        baseMapper.insert(address.toBuilder().memberId(RequestUtil.getLoginUser().getInteger("id")).build());
        responseJson.setData(address);
        responseJson.ok();
        return responseJson;
    }

    @Override
    @SimpleRestLogin
    public ResponseJson<MemberAddress> info(JSONObject json) {
        ResponseJson<MemberAddress> responseJson = new ResponseJson<>();
        Integer id = RequestUtil.getIntegerParam("id");
        responseJson.setData(baseMapper
                .selectOne(
                        new QueryWrapper<MemberAddress>().lambda().eq(MemberAddress::getId, id)));
        responseJson.ok();
        return responseJson;
    }

    @Override
    @SimpleRestLogin
    public ResponsePageJson<List<MemberAddress>> page(JSONObject json) {
        ResponsePageJson<List<MemberAddress>> responsePaginatorVo = new ResponsePageJson<>();
        Integer pageIndex = RequestUtil.getIntegerParam("pageIndex");
        Integer pageSize = RequestUtil.getIntegerParam("pageSize");
        Page<MemberAddress> page = new Page<>(pageIndex, pageSize);
        LambdaQueryWrapper<MemberAddress> lqw = new QueryWrapper<MemberAddress>().lambda();

        JSONObject query = json.getJSONObject("query");
        if (ObjectUtil.isNotEmpty(query)) {
        }
        lqw.eq(MemberAddress::getMemberId, RequestUtil.getLoginUser().getInteger("id"));
        lqw.eq(MemberAddress::getIsDelete, 0);
        IPage<MemberAddress> result = baseMapper.selectPage(page, lqw);
        responsePaginatorVo.setData(result.getRecords());
        responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
        responsePaginatorVo.ok();
        return responsePaginatorVo;
    }

    @Override
    @SimpleRestLogin
    public ResponseJson<String> update(JSONObject json) {
      ResponseJson<String> responseJson = new ResponseJson<>();
      AddressUpdateDto addressUpdateDto = JSON.toJavaObject(json, AddressUpdateDto.class);
      MemberAddress address = new MemberAddress();
      BeanUtils.copyProperties(addressUpdateDto, address);
      baseMapper.updateById(address);
      responseJson.setData("更新成功");
      responseJson.ok();
      return responseJson;
    }

    @Override
    @SimpleRestLogin
    public ResponseJson<String> delete(JSONObject json) {
      ResponseJson<String> responseJson = new ResponseJson<>();
      Integer id = RequestUtil.getIntegerParam("id");
      baseMapper.update(null, new UpdateWrapper<MemberAddress>()
        .lambda()
        .eq(MemberAddress::getId, id)
        .set(MemberAddress::getIsDelete, 1));
      responseJson.setData("删除成功");
      responseJson.ok();
      return responseJson;
    }

}
