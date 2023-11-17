package com.tacomall.apiweb.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.tacomall.apiweb.factory.PageFactory;
import com.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/page/")
public class PageController {

    @Autowired
    PageFactory pageFactory;

    @PostMapping("info")
    public ResponseJson<Object> info(@RequestParam(value = "key") String key, @RequestBody JSONObject json) {
        return pageFactory.getStrategy(key).info(json);
    }

}
