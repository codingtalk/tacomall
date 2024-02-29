package com.tacomall.apiadmin.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tacomall.apiadmin.service.MemberService;
import com.tacomall.apiadmin.vo.member.MemberInfoVo;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

@RestController
@RequestMapping(value = "/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("page")
    public ResponsePageJson<List<PaginatorVo>> page(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return memberService.page(pageIndex, pageSize, json);
    }

    @PostMapping("info")
    public ResponseJson<MemberInfoVo> info(@RequestParam(value = "id") Integer id) {
        return memberService.info(id);
    }

    @PostMapping("updateField")
    public ResponseJson<String> updateField(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "type") Integer type, @RequestBody JSONObject json) {
        return memberService.updateField(id, type, json);
    }

}