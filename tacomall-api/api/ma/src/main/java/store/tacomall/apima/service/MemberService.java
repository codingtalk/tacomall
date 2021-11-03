/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-21 14:21:23
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/MemberService.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.entity.member.Member;
import store.tacomall.common.json.ResponseJson;

public interface MemberService extends IService<Member> {

  ResponseJson<Member> register(String mobile, String passwd, String inviteCode, Integer type);

  ResponseJson<String> loginByMobile(String mobile, String passwd);

  ResponseJson<Member> info(JSONObject json);

}
