/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-27 10:59:38
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/ma/src/main/java/store/tacomall/apima/controller/GoodsController.java
 * @Just do what I think it is right
 */
package store.tacomall.apima.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import store.tacomall.apima.service.GoodsService;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.vo.base.PageVo;

@RestController
@RequestMapping(value = "/goods/")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @PostMapping("page")
    public ResponsePageJson<List<PageVo>> page(@RequestParam(value = "pageIndex") Integer pageIndex,
            @RequestParam(value = "pageSize") Integer pageSize, @RequestBody JSONObject json) {
        return goodsService.page(pageIndex, pageSize, json);
    }

}
