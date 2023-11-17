package com.tacomall.apima.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface MemberService extends IService<Member> {

  ResponseJson<Member> register(String mobile, String passwd, String inviteCode, Integer type);

  ResponseJson<String> loginByMobile(String mobile, String passwd);

  ResponseJson<Map<String, Object>> wxLogin(JSONObject json);

  ResponseJson<Member> info(JSONObject json);

  ResponseJson<String> update(JSONObject json);

  ResponsePageJson<List<PaginatorVo>> page(JSONObject json);

}