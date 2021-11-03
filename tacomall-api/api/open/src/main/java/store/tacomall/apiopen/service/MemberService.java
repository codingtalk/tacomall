/***
 * @Author: 码上talk|RC
 * @Date: 2021-08-14 08:11:58
 * @LastEditTime: 2021-10-10 21:22:59
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/service/MemberService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiopen.service;

import store.tacomall.common.json.ResponseJson;

import com.baomidou.mybatisplus.extension.service.IService;
import me.chanjar.weixin.common.error.WxErrorException;
import store.tacomall.common.entity.member.Member;

public interface MemberService extends IService<Member> {

  ResponseJson<String> inviteWxPic(Integer memberId, Integer isForceUpdate) throws WxErrorException;
}
