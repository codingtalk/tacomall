package com.tacomall.apiweb.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.logistic.Logistic;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface LogisticService extends IService<Logistic> {

  ResponseJson<Logistic> add(JSONObject json);
}
