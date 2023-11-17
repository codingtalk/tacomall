package com.tacomall.apima.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.member.MemberAddress;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface AddressService extends IService<MemberAddress> {

    ResponsePageJson<List<MemberAddress>> page(JSONObject json);

    ResponseJson<MemberAddress> info(JSONObject json);

    ResponseJson<MemberAddress> add(JSONObject json);

    ResponseJson<String> update(JSONObject json);

    ResponseJson<String> delete(JSONObject json);
    
}
