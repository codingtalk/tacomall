/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-20 15:01:29
 * @LastEditTime: 2021-10-26 15:23:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/MemberService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service;

import java.util.List;

import store.tacomall.apiadmin.vo.member.MemberInfoVo;
import store.tacomall.common.entity.member.Member;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.vo.base.PageVo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

public interface MemberService extends IService<Member> {

    ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

    ResponseJson<MemberInfoVo> info(Integer id);

    ResponseJson<String> updateField(Integer id, Integer type, JSONObject json);

}
