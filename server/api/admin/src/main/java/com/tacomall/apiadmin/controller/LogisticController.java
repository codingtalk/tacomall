package com.tacomall.apiadmin.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.tacomall.apiadmin.service.LogisticService;
import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

@RestController
@RequestMapping(value = "/logistic/")
public class LogisticController {

    @Autowired
    LogisticService logisticService;

    @PostMapping("add")
    public ResponseJson<Logistic> add(@RequestBody JSONObject json) {
        return logisticService.add(json);
    }

}