package com.tacomall.common.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.org.OrgDept;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;

public interface OrgDeptService extends IService<OrgDept> {

    ResponsePageJson<List<OrgDept>> page(Integer pageIndex, Integer pageSize, JSONObject json);

    ResponseJson<OrgDept> info(Integer id);

    ResponseJson<OrgDept> add(JSONObject json);

    ResponseJson<OrgDept> update(Integer id, JSONObject json);
}
