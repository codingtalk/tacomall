package com.tacomall.apima.controller;

import com.alibaba.fastjson.JSONObject;
import com.tacomall.apima.factory.PageFactory;
import com.tacomall.common.annotation.LoginUser;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/page/")
public class PageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PageFactory pageFactory;

    @LoginUser(required = false)
    @PostMapping(value = "info")
    public Object info(
            @RequestParam(value = "key") String key,
            @RequestBody JSONObject body) {
        return pageFactory.getStrategy(
                key).info(body);
    }
}
