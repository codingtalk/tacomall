/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-27 21:17:14
 * @LastEditTime: 2021-11-05 14:36:24
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/controller/OrderFormController.java
 */
package store.tacomall.apima.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.annotation.LoginUser;
import store.tacomall.common.bo.orderForm.OrderFormPayReqBo;
import store.tacomall.common.bo.orderForm.OrderFormPayRetBo;
import store.tacomall.apima.service.OrderFormService;
import store.tacomall.common.entity.order.OrderForm;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.logic.OrderFormPayLogic;
import store.tacomall.common.vo.base.PageVo;

@RestController
@RequestMapping(value = "/orderForm/")
public class OrderFormController {

    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private OrderFormPayLogic orderFormPayLogic;

    @LoginUser
    @PostMapping("add")
    public ResponseJson<OrderForm> add(@RequestParam(value = "type") String type, @RequestBody JSONObject json) {
        return orderFormService.add(type, json);
    }

    @LoginUser
    @PostMapping("calculate")
    public ResponseJson<OrderFormPayRetBo> calculate(@RequestParam(value = "id") Integer id,
            @RequestBody OrderFormPayReqBo orderFormPayReqBo) throws Exception {
        ResponseJson<OrderFormPayRetBo> responseJson = new ResponseJson<>();
        responseJson.setData(orderFormPayLogic.calculate(id, orderFormPayReqBo));
        responseJson.ok();
        return responseJson;
    }

    @LoginUser
    @PostMapping("settle")
    public ResponseJson<OrderFormPayRetBo> settle(@RequestParam(value = "id") Integer id,
            @RequestBody OrderFormPayReqBo orderFormPayReqBo) throws Exception {
        ResponseJson<OrderFormPayRetBo> responseJson = new ResponseJson<>();
        responseJson.setData(orderFormPayLogic.settle(id, orderFormPayReqBo));
        responseJson.ok();
        return responseJson;
    }

    @LoginUser
    @PostMapping("page")
    public ResponsePageJson<List<PageVo>> page(@RequestParam(value = "pageIndex") Integer pageIndex,
            @RequestParam(value = "pageSize") Integer pageSize, @RequestBody JSONObject json) {
        return orderFormService.page(pageIndex, pageSize, json);
    }

    @LoginUser
    @PostMapping("info")
    public ResponseJson<OrderForm> info(@RequestParam(value = "id") Integer id) {
        return orderFormService.info(id);
    }

}