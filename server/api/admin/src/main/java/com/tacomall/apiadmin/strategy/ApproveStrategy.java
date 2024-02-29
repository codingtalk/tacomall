
package com.tacomall.apiadmin.strategy;

import com.alibaba.fastjson.JSONObject;
import com.tacomall.common.json.ResponseJson;

public interface ApproveStrategy {

  ResponseJson<Object> doAgree(Integer id, Integer isAgree,
                        JSONObject json);

  ResponseJson<Object> cancel(Integer id, JSONObject json);

}
