/***
 * @Author: 码上talk|RC
 * @Date: 2020-11-20 14:56:48
 * @LastEditTime: 2021-10-06 14:23:37
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/strategy/impl/StatisticsDashbordStrategyImpl.java
 * @微信:  13680065830
 * @邮箱:  3189482282@qq.com
 * @oops: Just do what I think it is right
 */
package store.tacomall.apiadmin.strategy.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;

import store.tacomall.common.json.ResponseJson;
import store.tacomall.apiadmin.strategy.StatisticsStrategy;

@Component("dashbord")
public class StatisticsDashbordStrategyImpl implements StatisticsStrategy {

  @Override
  public ResponseJson<List<Map<String, Object>>> query(JSONObject json) {
    ResponseJson<List<Map<String, Object>>> responseJson = new ResponseJson<>();
    responseJson.setData(new ArrayList<Map<String, Object>>());
    responseJson.ok();
    return responseJson;
  }
}
