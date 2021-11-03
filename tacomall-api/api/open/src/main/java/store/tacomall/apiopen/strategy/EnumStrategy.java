/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-10 16:59:34
 * @LastEditTime: 2021-10-06 14:18:08
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/open/src/main/java/store/tacomall/apiopen/strategy/EnumStrategy.java
 * @Just do what I think it is right
 */
package store.tacomall.apiopen.strategy;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import store.tacomall.common.json.ResponseJson;

public interface EnumStrategy {

  ResponseJson<List<Map<String, Object>>> query(JSONObject json);

}
