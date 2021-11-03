/***
 * @Author: 码上talk|RC
 * @Date: 2021-03-11 20:58:27
 * @LastEditTime: 2021-10-22 14:05:35
 * @LastEditors: 码上talk|RC
 * @Description:
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/TmDeptService.java
 * @微信: 13680065830
 * @邮箱: 3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.entity.tm.TmDept;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.json.ResponseJson;

public interface TmDeptService extends IService<TmDept> {

  ResponsePageJson<List<TmDept>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<TmDept> info(Integer id);

  ResponseJson<TmDept> add(JSONObject json);

  ResponseJson<String> update(JSONObject json);
}
