/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-27 21:04:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/controller/MemberController.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.annotation.LoginUser;
import store.tacomall.apima.service.*;
import store.tacomall.common.entity.member.*;
import store.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/member/")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberCartService memberCartService;

    @PostMapping("register")
    public ResponseJson<Member> register(@RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "passwd") String passwd, @RequestParam(value = "inviteCode") String inviteCode,
            @RequestParam(value = "type", defaultValue = "1") Integer type) {
        return memberService.register(mobile, passwd, inviteCode, type);
    }

    @PostMapping("loginByMobile")
    public ResponseJson<String> loginByMobile(@RequestParam(value = "mobile") String mobile,
            @RequestParam(value = "passwd") String passwd) {
        return memberService.loginByMobile(mobile, passwd);
    }

    @LoginUser
    @PostMapping("info")
    public ResponseJson<Member> info(@RequestBody JSONObject json) {
        return memberService.info(json);
    }

    @LoginUser
    @PostMapping("cartAdd")
    public ResponseJson<String> cartAdd(@RequestBody JSONObject json) {
        return memberCartService.add(json);
    }

}
