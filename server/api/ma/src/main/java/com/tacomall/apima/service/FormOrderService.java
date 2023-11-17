package com.tacomall.apima.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.apima.vo.form.FormOrderAddVo;
import com.tacomall.common.entity.form.FormOrder;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface FormOrderService extends IService<FormOrder> {

  ResponsePageJson<List<PaginatorVo>> page(JSONObject json);

  ResponseJson<FormOrder> info(JSONObject json);

  ResponseJson<FormOrderAddVo> add(JSONObject json);

}
