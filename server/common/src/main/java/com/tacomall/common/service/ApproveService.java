package com.tacomall.common.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.approve.Approve;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.vo.base.PaginatorVo;

public interface ApproveService extends IService<Approve> {

    ResponsePageJson<List<PaginatorVo>> page(Integer pageIndex, Integer pageSize, JSONObject json);

    ResponseJson<Approve> info(Integer id);

    ResponseJson<Approve> add(Integer typeId, Integer relateId);

    ResponseJson<String> doAgree(Integer id, Integer isAgree, JSONObject json);

    ResponseJson<String> cancel(Integer id, JSONObject json);
}
