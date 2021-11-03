/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-10 16:59:34
 * @LastEditTime: 2021-10-06 14:05:09
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/strategy/StatisticsStrategy.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin.strategy;

import store.tacomall.common.json.ResponseJson;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface StatisticsStrategy {

  ResponseJson<List<Map<String, Object>>> query(JSONObject json);

}
