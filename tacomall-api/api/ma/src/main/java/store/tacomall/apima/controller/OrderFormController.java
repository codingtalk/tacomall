/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-27 21:17:14
 * @LastEditTime: 2021-11-01 17:53:34
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/controller/OrderFormController.java
 */
package store.tacomall.apima.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.annotation.LoginUser;
import store.tacomall.apima.service.OrderFormService;
import store.tacomall.common.entity.order.OrderForm;
import store.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/orderForm/")
public class OrderFormController {

    @Autowired
    private OrderFormService orderFormService;

    @LoginUser
    @PostMapping("add")
    public ResponseJson<OrderForm> add(@RequestParam(value = "type") String type, @RequestBody JSONObject json) {
        return orderFormService.add(type, json);
    }

}