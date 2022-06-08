/***
 * @Author: 码上talk|RC/3189482282@qq.com
 * @Date: 2021-10-27 21:17:14
 * @LastEditTime: 2022-01-05 14:36:17
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/controller/SaleController.java
 */
package store.tacomall.apima.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import store.tacomall.common.annotation.LoginUser;
import store.tacomall.common.bo.pay.PayReqBo;
import store.tacomall.common.bo.pay.PayResBo;
import store.tacomall.apima.service.SaleFormService;
import store.tacomall.apima.vo.sale.SaleFormPayVo;
import store.tacomall.common.entity.sale.SaleForm;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.logic.sale.SaleFormPayLogic;
import store.tacomall.common.vo.base.PageVo;

@RestController
@RequestMapping(value = "/sale/")
public class SaleController {

  @Autowired
  private SaleFormService saleFormService;

  @Autowired
  private SaleFormPayLogic saleFormPayLogic;

  @LoginUser
  @PostMapping("formAdd")
  public ResponseJson<SaleForm> add(@RequestParam(value = "type") String type, @RequestBody JSONObject json) {
    return saleFormService.add(type, json);
  }

  @LoginUser
  @PostMapping("pay")
  public ResponseJson<SaleFormPayVo> pay(@RequestParam(value = "id") Integer id,
      @RequestParam(value = "isPreview") Integer isPreview, @RequestBody PayReqBo payReqBo) throws Exception {
    return saleFormService.pay(id, isPreview, payReqBo);
  }

  @LoginUser
  @PostMapping("formPage")
  public ResponsePageJson<List<PageVo>> page(@RequestParam(value = "pageIndex") Integer pageIndex,
      @RequestParam(value = "pageSize") Integer pageSize, @RequestBody JSONObject json) {
    return saleFormService.page(pageIndex, pageSize, json);
  }

  @LoginUser
  @PostMapping("formInfo")
  public ResponseJson<SaleForm> info(@RequestParam(value = "id") Integer id) {
    return saleFormService.info(id);
  }

}