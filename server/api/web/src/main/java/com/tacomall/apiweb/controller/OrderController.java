package com.tacomall.apiweb.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.tacomall.apiweb.dto.FormOrderUpdateDto;
import com.tacomall.apiweb.service.FormOrderService;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

@RestController
@RequestMapping(value = "/order/")
public class OrderController {

    @Autowired
    FormOrderService formOrderService;

    @PostMapping("formPage")
    public ResponsePageJson<List<PaginatorVo>> page(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return formOrderService.page(pageIndex, pageSize, json);
    }

    @PostMapping("formInfo")
    public ResponseJson<FormOrder> info(@RequestParam(value = "id") Integer id) {
        return formOrderService.info(id);
    }

    @PostMapping("formUpdate")
    public ResponseJson<String> update(@RequestParam(value = "id") Integer id, @RequestBody FormOrderUpdateDto formOrderUpdateDto) {
        return formOrderService.update(id, formOrderUpdateDto);
    }

}