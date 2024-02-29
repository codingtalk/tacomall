package com.tacomall.apiadmin.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.apiadmin.dto.FormOrderUpdateDto;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface FormOrderService extends IService<FormOrder> {

  ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

  ResponseJson<FormOrder> info(Integer id);

  ResponseJson<FormOrder> add(JSONObject json);

  ResponseJson<String> update(Integer id, FormOrderUpdateDto formOrderUpdateDto);
}
