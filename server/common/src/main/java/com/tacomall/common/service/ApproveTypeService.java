package com.tacomall.common.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.approve.ApproveType;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface ApproveTypeService extends IService<ApproveType> {

    ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

    ResponseJson<ApproveType> info(Integer id);

    ResponseJson<String> update(Integer id, JSONObject json);

}