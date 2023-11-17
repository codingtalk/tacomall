package com.tacomall.apiweb.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.tacomall.apiweb.factory.ApproveFactory;
import com.tacomall.common.entity.approve.Approve;
import com.tacomall.common.entity.approve.ApproveType;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.service.ApproveService;
import com.tacomall.common.service.ApproveTypeService;
import com.tacomall.common.vo.base.PaginatorVo;

@RestController
@RequestMapping(value = "/approve/")
public class ApproveController {

    @Autowired
    ApproveService approveService;

    @Autowired
    ApproveTypeService approveTypeService;

    @Autowired
    ApproveFactory approveFactory;

    @PostMapping("page")
    public ResponsePageJson<List<PaginatorVo>> page(@RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return approveService.page(pageIndex, pageSize, json);
    }

    @PostMapping("info")
    public ResponseJson<Approve> info(@RequestParam(value = "id") Integer id) {
        return approveService.info(id);
    }

    @PostMapping("add")
    public ResponseJson<Approve> add(@RequestParam(value = "typeId") Integer typeId,
            @RequestParam(value = "relateId") Integer relateId) {
        return approveService.add(typeId, relateId);
    }

    @PostMapping("doAgree")
    public ResponseJson<String> doAgree(@RequestParam(value = "id") Integer id,
            @RequestParam(value = "isAgree") Integer isAgree, @RequestBody JSONObject json) {
        return approveService.doAgree(id, isAgree, json);
    }

    @PostMapping("cancel")
    public ResponseJson<String> cancel(@RequestParam(value = "id") Integer id,
            @RequestBody JSONObject json) {
        return approveService.cancel(id, json);
    }

    @PostMapping("typePage")
    public ResponsePageJson<List<PaginatorVo>> typePage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return approveTypeService.page(pageIndex, pageSize, json);
    }

    @PostMapping("typeInfo")
    public ResponseJson<ApproveType> typeInfo(@RequestParam(value = "id") Integer id) {
        return approveTypeService.info(id);
    }

    @PostMapping("typeUpdate")
    public ResponseJson<String> typeUpdate(@RequestParam(value = "id") Integer id, @RequestBody JSONObject json) {
        return approveTypeService.update(id, json);
    }

}