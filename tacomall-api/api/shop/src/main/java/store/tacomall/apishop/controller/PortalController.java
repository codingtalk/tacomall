package store.tacomall.apishop.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import store.tacomall.common.annotation.SimpleController;
import store.tacomall.common.json.ResponseJson;

@RestController
@RequestMapping(value = "/{domain}/")
public class PortalController {

    @GetMapping("{action}")
    @SimpleController
    public ResponseJson<Object> index(@PathVariable Map<String, String> pathVariableMap) {
        return new ResponseJson<Object>();
    }

}
