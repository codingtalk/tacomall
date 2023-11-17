package com.tacomall.apiopen.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tacomall.apiopen.service.MemberService;

import com.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("inviteCode")
    public ResponseJson<String> inviteCode(@RequestParam(value = "memberId") Integer memberId,
                                                        @RequestParam(value = "isForceUpdate", defaultValue = "0") Integer isForceUpdate) throws WxErrorException {
        return memberService.inviteCode(memberId, isForceUpdate);
    }
}

