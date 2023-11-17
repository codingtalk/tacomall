package com.tacomall.common.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.org.OrgAccessRule;
import com.tacomall.common.json.ResponseJson;

import java.util.List;

public interface OrgAccessRuleService extends IService<OrgAccessRule> {

    ResponseJson<List<OrgAccessRule>> list(JSONObject json);

}
