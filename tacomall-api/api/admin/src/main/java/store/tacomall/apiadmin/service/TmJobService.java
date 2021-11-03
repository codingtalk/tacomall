/***
 * @Author: 码上talk|RC
 * @Date: 2021-01-18 15:00:44
 * @LastEditTime: 2021-10-22 14:05:41
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/TmJobService.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.cn
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.entity.tm.TmJob;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.json.ResponseJson;

public interface TmJobService extends IService<TmJob> {

  ResponsePageJson<List<TmJob>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<TmJob> info(Integer id);

  ResponseJson<TmJob> add(JSONObject json);

  ResponseJson<String> update(JSONObject json);

}