/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-10 21:28:31
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/controller/MemberController.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen.controller;

import me.chanjar.weixin.common.error.WxErrorException;
import store.tacomall.apiopen.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/member")
public class MemberController {

  @Autowired
  private MemberService memberService;

  @PostMapping("inviteWxPic")
  public ResponseJson<String> memberInviteCodeWxEntry(@RequestParam(value = "memberId") Integer memberId,
      @RequestParam(value = "isForceUpdate", defaultValue = "0") Integer isForceUpdate) throws WxErrorException {
    return memberService.inviteWxPic(memberId, isForceUpdate);
  }
}
