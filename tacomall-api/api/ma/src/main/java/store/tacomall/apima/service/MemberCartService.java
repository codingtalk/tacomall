/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-27 21:01:34
 * @LastEditTime: 2021-10-27 21:02:40
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/MemberCartService.java
 */
package store.tacomall.apima.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.entity.member.MemberCart;
import store.tacomall.common.json.ResponseJson;

public interface MemberCartService extends IService<MemberCart> {

    ResponseJson<String> add(JSONObject json);

}