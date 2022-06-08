/**
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-03-12 16:03:33
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/cn/tacomall/apiadmin/controller/TmController.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin.controller;

import java.util.List;

import javax.validation.Valid;

import com.alibaba.fastjson.JSONObject;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import store.tacomall.common.entity.tm.TmStaff;
import store.tacomall.common.entity.tm.TmDept;
import store.tacomall.common.entity.tm.TmJob;
import store.tacomall.common.entity.tm.TmAccessRule;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.vo.base.PageVo;
import store.tacomall.apiadmin.annotation.TmLoginLog;
import store.tacomall.apiadmin.service.TmStaffService;
import store.tacomall.apiadmin.valid.tm.TmStaffAddValid;
import store.tacomall.apiadmin.service.TmAccessRuleService;
import store.tacomall.apiadmin.service.TmDeptService;
import store.tacomall.apiadmin.service.TmJobService;

@RestController
@RequestMapping(value = "/tm/")
public class TmController {

    @Autowired
    private TmStaffService tmStaffService;

    @Autowired
    private TmAccessRuleService tmAccessRuleService;

    @Autowired
    private TmDeptService tmDeptService;

    @Autowired
    private TmJobService tmJobService;

    @TmLoginLog()
    @PostMapping("staffLogin")
    public ResponseJson<String> staffLogin(@RequestParam(value = "username") String username,
            @RequestParam(value = "passwd") String passwd) {
        return tmStaffService.login(username, passwd);
    }

    @PostMapping("staffLogout")
    public ResponseJson<String> logout() {
        return tmStaffService.logout();
    }

    @PostMapping("staffInfo")
    public ResponseJson<TmStaff> info(@RequestParam(value = "id", defaultValue = "0") Integer id) {
        return tmStaffService.info(id);
    }

    @PostMapping("staffAccessRuleList")
    public ResponseJson<List<TmAccessRule>> rules() {
        return tmStaffService.accessRuleList();
    }

    @PostMapping("staffPage")
    public ResponsePageJson<List<PageVo>> staffPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return tmStaffService.page(pageIndex, pageSize, json);
    }

    @PostMapping("staffAdd")
    public ResponseJson<TmStaff> staffAdd(@Valid @RequestBody TmStaffAddValid json) {
        return tmStaffService.add(json);
    }

    @PostMapping("staffUpdate")
    public ResponseJson<String> staffUpdate(@RequestBody JSONObject json) {
        return tmStaffService.update(json);
    }

    @PostMapping("deptPage")
    public ResponsePageJson<List<TmDept>> deptPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return tmDeptService.page(pageIndex, pageSize, json);
    }

    @PostMapping("deptInfo")
    public ResponseJson<TmDept> deptInfo(@RequestParam(value = "id") Integer id) {
        return tmDeptService.info(id);
    }

    @PostMapping("deptAdd")
    public ResponseJson<TmDept> deptAdd(@RequestBody JSONObject json) {
        return tmDeptService.add(json);
    }

    @PostMapping("deptUpdate")
    public ResponseJson<String> deptUpdate(@RequestBody JSONObject json) {
        return tmDeptService.update(json);
    }

    @PostMapping("jobPage")
    public ResponsePageJson<List<TmJob>> jobPage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return tmJobService.page(pageIndex, pageSize, json);
    }

    @PostMapping("jobInfo")
    public ResponseJson<TmJob> jobInfo(@RequestParam(value = "id") Integer id) {
        return tmJobService.info(id);
    }

    @PostMapping("jobAdd")
    public ResponseJson<TmJob> jobAdd(@RequestBody JSONObject json) {
        return tmJobService.add(json);
    }

    @PostMapping("jobUpdate")
    public ResponseJson<String> jobUpdate(@RequestBody JSONObject json) {
        return tmJobService.update(json);
    }

    @PostMapping("accessRulePage")
    public ResponsePageJson<List<TmAccessRule>> accessRulePage(
            @RequestParam(value = "pageIndex", defaultValue = "1") Integer pageIndex,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, @RequestBody JSONObject json) {
        return tmAccessRuleService.page(pageIndex, pageSize, json);
    }

    @PostMapping("accessRuleInfo")
    public ResponseJson<TmAccessRule> accessRuleInfo(@RequestParam(value = "id") Integer id) {
        return tmAccessRuleService.info(id);
    }

    @PostMapping("accessRuleAdd")
    public ResponseJson<TmAccessRule> accessRuleAdd(@RequestBody JSONObject json) {
        return tmAccessRuleService.add(json);
    }

    @PostMapping("accessRuleUpdate")
    public ResponseJson<String> accessRuleUpdate(@RequestBody JSONObject json) {
        return tmAccessRuleService.update(json);
    }

}
