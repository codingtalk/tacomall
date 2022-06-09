package store.tacomall.apishop.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSONObject;

import store.tacomall.common.annotation.SimpleRestController;
import store.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/{domain}/")
public class PortalController {

    @PostMapping("{action}")
    @SimpleRestController
    public ResponseJson<Object> index(@PathVariable Map<String, String> pathVariableMap,
            @RequestBody JSONObject body) {
        return new ResponseJson<Object>();
    }

}
