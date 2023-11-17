
package com.tacomall.apima.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;
import com.tacomall.common.annotation.SimpleRestController;

@RestController
@RequestMapping(value = "/{domain}/")
public class MainController {

    @PostMapping("{action}")
    @SimpleRestController
    public Object index(@PathVariable Map<String, String> pathVariableMap,
            @RequestBody JSONObject body) {
        return new Object();
    }

}
