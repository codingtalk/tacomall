
package com.tacomall.common.service;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tacomall.common.entity.org.OrgAccessRule;
import com.tacomall.common.entity.org.OrgStaff;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;

public interface OrgStaffService extends IService<OrgStaff> {

    ResponseJson<String> login(String username, String password);

    ResponseJson<String> logout();

    ResponseJson<OrgStaff> info(Integer id);

    ResponseJson<List<OrgAccessRule>> accessRuleList();

    ResponsePageJson<List<OrgStaff>> page(Integer pageIndex, Integer pageSize, JSONObject json);

    ResponseJson<OrgStaff> add(JSONObject json);

    ResponseJson<String> update(Integer id, JSONObject json);
}
