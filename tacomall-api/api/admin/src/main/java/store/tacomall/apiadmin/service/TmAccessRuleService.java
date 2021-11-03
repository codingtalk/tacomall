/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-10 14:21:34
 * @LastEditTime: 2021-10-22 14:05:30
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/TmAccessRuleService.java
 */
package store.tacomall.apiadmin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.entity.tm.TmAccessRule;

public interface TmAccessRuleService extends IService<TmAccessRule> {

  ResponsePageJson<List<TmAccessRule>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<TmAccessRule> info(Integer id);

  ResponseJson<TmAccessRule> add(JSONObject json);

  ResponseJson<String> update(JSONObject json);

}