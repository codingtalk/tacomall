/***
 * @Author: 码上talk|RC
 * @Date: 2020-07-10 16:59:34
 * @LastEditTime: 2021-10-26 17:28:48
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/strategy/PageStrategy.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.strategy;

import store.tacomall.common.json.ResponseJson;

import com.alibaba.fastjson.JSONObject;

public interface PageStrategy {

    ResponseJson<Object> loadPageInfo(JSONObject json);

}
