package com.tacomall.apiweb.service;

import java.util.List;

import com.tacomall.apiweb.vo.member.MemberInfoVo;
import com.tacomall.common.entity.member.Member;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MemberService extends IService<Member> {

  ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<MemberInfoVo> info(Integer id);

  ResponseJson<String> updateField(Integer id, Integer type, JSONObject json);

}