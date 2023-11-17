package com.tacomall.common.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.org.OrgJob;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;

public interface OrgJobService extends IService<OrgJob> {

    ResponsePageJson<List<OrgJob>> page(Integer pageIndex, Integer pageSize, JSONObject json);

    ResponseJson<OrgJob> info(Integer id);

    ResponseJson<OrgJob> add(JSONObject json);

    ResponseJson<OrgJob> update(Integer id, JSONObject json);

}