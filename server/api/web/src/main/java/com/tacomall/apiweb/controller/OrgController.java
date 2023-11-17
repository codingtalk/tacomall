
package com.tacomall.apiweb.controller;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.tacomall.common.annotation.OrgStaffLoginLog;
import com.tacomall.common.entity.org.OrgAccessRule;
import com.tacomall.common.entity.org.OrgDept;
import com.tacomall.common.entity.org.OrgJob;
import com.tacomall.common.entity.org.OrgStaff;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.service.OrgAccessRuleService;
import com.tacomall.common.service.OrgDeptService;
import com.tacomall.common.service.OrgJobService;
import com.tacomall.common.service.OrgStaffService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping(value = "/org/")
public class OrgController {

    @Autowired
    OrgStaffService orgStaffService;

    @Autowired
    OrgDeptService orgDeptService;

    @Autowired
    OrgJobService orgJobService;

    @Autowired
    OrgAccessRuleService orgAccessRuleService;

    @OrgStaffLoginLog()
    @PostMapping("staffLogin")
    public ResponseJson<String> staffLogin(@RequestParam(value = "username") String username,
            @RequestParam(value = "passwd") String passwd) {
        return orgStaffService.login(username, passwd);
    }

    @PostMapping("staffLogout")
    public ResponseJson<String> logout() {
        return orgStaffService.logout();
    }

    @PostMapping("staffInfo")
    public ResponseJson<OrgStaff> info(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        return orgStaffService.info(id);
    }

    @PostMapping("staffAccessRuleList")
    public ResponseJson<List<OrgAccessRule>> rules() {
        return orgStaffService.accessRuleList();
    }

    @PostMapping("staffPage")
    public ResponsePageJson<List<OrgStaff>> staffPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return orgStaffService.page(pageIndex, pageSize, json);
    }

    @PostMapping("staffAdd")
    public ResponseJson<OrgStaff> staffAdd(@RequestBody JSONObject json) {
        return orgStaffService.add(json);
    }

    @PostMapping("staffUpdate")
    public ResponseJson<String> staffUpdate(@RequestParam(value = "id", defaultValue = "0") Integer id,
            @RequestBody JSONObject json) {
        return orgStaffService.update(id, json);
    }

    @PostMapping("deptPage")
    public ResponsePageJson<List<OrgDept>> deptPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return orgDeptService.page(pageIndex, pageSize, json);
    }

    @PostMapping("deptInfo")
    public ResponseJson<OrgDept> deptInfo(@RequestParam(value = "id") Integer id) {
        return orgDeptService.info(id);
    }

    @PostMapping("deptAdd")
    public ResponseJson<OrgDept> deptAdd(@RequestBody JSONObject json) {
        return orgDeptService.add(json);
    }

    @PostMapping("deptUpdate")
    public ResponseJson<OrgDept> deptUpdate(@RequestParam(value = "id") Integer id, @RequestBody JSONObject json) {
        return orgDeptService.update(id, json);
    }

    @PostMapping("jobPage")
    public ResponsePageJson<List<OrgJob>> jobPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return orgJobService.page(pageIndex, pageSize, json);
    }

    @PostMapping("jobInfo")
    public ResponseJson<OrgJob> jobInfo(@RequestParam(value = "id") Integer id) {
        return orgJobService.info(id);
    }

    @PostMapping("jobAdd")
    public ResponseJson<OrgJob> jobAdd(@RequestBody JSONObject json) {
        return orgJobService.add(json);
    }

    @PostMapping("jobUpdate")
    public ResponseJson<OrgJob> jobUpdate(@RequestParam(value = "id") Integer id, @RequestBody JSONObject json) {
        return orgJobService.update(id, json);
    }

    @PostMapping("accessRuleList")
    public ResponseJson<List<OrgAccessRule>> accessRuleList(@RequestBody JSONObject json) {
        return orgAccessRuleService.list(json);
    }

}
