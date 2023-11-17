
package com.tacomall.apiweb.strategy;

import com.alibaba.fastjson.JSONObject;
import com.tacomall.common.json.ResponseJson;

public interface PageStrategy {

    ResponseJson<Object> info(JSONObject json);

}
