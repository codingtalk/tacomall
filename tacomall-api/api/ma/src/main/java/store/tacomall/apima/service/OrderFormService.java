/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-11-01 17:49:04
 * @LastEditTime: 2021-11-01 17:53:43
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/service/OrderFormService.java
 */
package store.tacomall.apima.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;

import store.tacomall.common.entity.order.OrderForm;
import store.tacomall.common.json.ResponseJson;

public interface OrderFormService extends IService<OrderForm> {

    ResponseJson<OrderForm> add(String type, JSONObject json);

}
