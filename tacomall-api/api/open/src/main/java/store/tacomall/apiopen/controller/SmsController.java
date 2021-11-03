/***
 * @Author: 码上talk|RC
 * @Date: 2021-08-02 14:28:11
 * @LastEditTime: 2021-10-10 15:32:02
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/controller/SmsController.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiopen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.apiopen.service.SmsService;

@RestController
@RequestMapping(value = "/sms")
public class SmsController {

  @Autowired
  private SmsService smsService;

  @PostMapping("verifyCode")
  public ResponseJson<String> verifyCode(@RequestParam(value = "app") String app,
      @RequestParam(value = "bizType") Integer bizType, @RequestParam(value = "mobile") String mobile)
      throws Exception {
    return smsService.send(app, bizType, mobile);
  }

}