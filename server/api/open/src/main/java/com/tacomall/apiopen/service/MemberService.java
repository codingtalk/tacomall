package com.tacomall.apiopen.service;

import com.tacomall.common.entity.member.Member;
import com.tacomall.common.json.ResponseJson;

import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.common.error.WxErrorException;

public interface MemberService extends IService<Member> {

    ResponseJson<String> inviteCode(Integer memberId, Integer isForceUpdate) throws WxErrorException;

}
