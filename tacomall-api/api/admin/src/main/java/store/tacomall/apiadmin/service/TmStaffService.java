/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-22 17:23:18
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/TmStaffService.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.apiadmin.valid.tm.TmStaffAddValid;
import store.tacomall.common.entity.tm.TmAccessRule;
import store.tacomall.common.entity.tm.TmStaff;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.vo.base.PageVo;
import store.tacomall.common.json.ResponseJson;

public interface TmStaffService extends IService<TmStaff> {

    ResponseJson<String> login(String username, String password);

    ResponseJson<String> logout();

    ResponseJson<TmStaff> info(Integer id);

    ResponseJson<List<TmAccessRule>> accessRuleList();

    ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

    ResponseJson<TmStaff> add(TmStaffAddValid json);

    ResponseJson<String> update(JSONObject json);
}
