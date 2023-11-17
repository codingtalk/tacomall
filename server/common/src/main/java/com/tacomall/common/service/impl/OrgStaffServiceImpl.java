
package com.tacomall.common.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tacomall.common.entity.org.OrgAccessRule;
import com.tacomall.common.entity.org.OrgDept;
import com.tacomall.common.entity.org.OrgJob;
import com.tacomall.common.entity.org.OrgStaff;
import com.tacomall.common.json.ResponseJson;
import com.tacomall.common.json.ResponsePageJson;
import com.tacomall.common.mapper.org.OrgAccessRuleMapper;
import com.tacomall.common.mapper.org.OrgDeptMapper;
import com.tacomall.common.mapper.org.OrgJobMapper;
import com.tacomall.common.mapper.org.OrgStaffMapper;
import com.tacomall.common.service.OrgStaffService;
import com.tacomall.common.util.PasswordUtil;
import com.tacomall.common.util.StringUtil;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

@Service
public class OrgStaffServiceImpl extends ServiceImpl<OrgStaffMapper, OrgStaff> implements OrgStaffService {

  @Autowired
  private OrgAccessRuleMapper adminAuthRuleMapper;

  @Autowired
  private OrgDeptMapper orgDeptMapper;

  @Autowired
  private OrgJobMapper orgJobMapper;

  @Autowired
  Environment environment;

  @Autowired
  TransactionDefinition transactionDefinition;

  @Autowired
  DataSourceTransactionManager dataSourceTransactionManager;

  @Override
  public ResponseJson<String> login(String username, String password) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    try {
      Subject subject = SecurityUtils.getSubject();
      UsernamePasswordToken adminUser = new UsernamePasswordToken(username, PasswordUtil.encode(password));
      subject.login(adminUser);
      String authorization = (String) subject.getSession().getId();
      responseJson.setData(authorization);
      responseJson.ok();
    } catch (UnknownAccountException ue) {
      responseJson.setStatus(false);
      responseJson.setMessage("员工不存在");
    } catch (LockedAccountException le) {
      responseJson.setStatus(false);
      responseJson.setMessage("员工已冻结");
    } catch (IncorrectCredentialsException ie) {
      responseJson.setStatus(false);
      responseJson.setMessage("密码错误");
    }
    return responseJson;
  }

  @Override
  public ResponseJson<String> logout() {
    ResponseJson<String> responseJson = new ResponseJson<>();
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<OrgStaff> info(Integer id) {
    ResponseJson<OrgStaff> responseJson = new ResponseJson<>();
    OrgStaff orgStaff = (OrgStaff) SecurityUtils.getSubject().getPrincipal();
    responseJson.setData(baseMapper
        .selectOne(
            new QueryWrapper<OrgStaff>().lambda().eq(OrgStaff::getId, id.equals(0) ? orgStaff.getId() : id)));
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponseJson<List<OrgAccessRule>> accessRuleList() {
    ResponseJson<List<OrgAccessRule>> responseJson = new ResponseJson<>();
    OrgStaff orgStaff = (OrgStaff) SecurityUtils.getSubject().getPrincipal();
    OrgDept orgDept = orgDeptMapper.selectById(orgStaff.getDeptId());
    OrgJob orgJob = orgJobMapper.selectById(orgStaff.getJobId());
    responseJson.setData(new ArrayList<>());
    if (ObjectUtil.isNotNull(orgDept) && ObjectUtil.isNotEmpty(orgDept.getAccessRuleIds())) {
      responseJson.setData(
          adminAuthRuleMapper.selectList(new QueryWrapper<OrgAccessRule>().lambda().in(OrgAccessRule::getId,
              Arrays.asList(orgDept.getAccessRuleIds().split(",")).stream().filter((String s) -> {
                if (ObjectUtil.isNotNull(orgJob)
                    && ObjectUtil.isNotNull(orgJob.getIgnoreAccessRuleIds())
                    && Arrays.asList(orgJob.getIgnoreAccessRuleIds().split(",")).contains(s)) {
                  return false;
                }
                return true;
              }).collect(Collectors.toList()))));
    }
    responseJson.ok();
    return responseJson;
  }

  @Override
  public ResponsePageJson<List<OrgStaff>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
    ResponsePageJson<List<OrgStaff>> responsePaginatorVo = new ResponsePageJson<>();
    Page<OrgStaff> page = new Page<>(pageIndex, pageSize);
    LambdaQueryWrapper<OrgStaff> q = new QueryWrapper<OrgStaff>().lambda();

    JSONObject query = json.getJSONObject("query");
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.get("keyword"))) {
      q.like(OrgStaff::getNickname, query.get("keyword"));
    }
    if (ObjectUtil.isNotEmpty(query) && ObjectUtil.isNotEmpty(query.getInteger("deptId"))) {
      q.eq(OrgStaff::getDeptId, query.getInteger("deptId"));
    }
    q.eq(OrgStaff::getIsDelete, 0);
    IPage<OrgStaff> result = baseMapper.queryPage(page, q);
    responsePaginatorVo.setData(result.getRecords());
    responsePaginatorVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
    responsePaginatorVo.ok();
    return responsePaginatorVo;
  }

  @Override
  public ResponseJson<OrgStaff> add(JSONObject json) {
    ResponseJson<OrgStaff> responseJson = new ResponseJson<>();
    responseJson.setStatus(false);
    OrgStaff orgStaff = JSON.toJavaObject(json, OrgStaff.class);
    orgStaff.setPasswd(PasswordUtil.encode(orgStaff.getPasswd()));
    baseMapper.insert(orgStaff);
    responseJson.ok();
    responseJson.setData(orgStaff);
    return responseJson;
  }

  @Override
  public ResponseJson<String> update(Integer id, JSONObject json) {
    ResponseJson<String> responseJson = new ResponseJson<>();
    OrgStaff orgStaff = (OrgStaff) SecurityUtils.getSubject().getPrincipal();
    LambdaUpdateWrapper<OrgStaff> luw = new UpdateWrapper<OrgStaff>().lambda().eq(OrgStaff::getId,
        id.equals(0) ? orgStaff.getId() : id);
    if (StringUtil.isNotBlank(json.getString("nickname"))) {
      luw.set(OrgStaff::getNickname, json.getString("nickname"));
    }
    if (StringUtil.isNotBlank(json.getString("passwd"))) {
      luw.set(OrgStaff::getPasswd, PasswordUtil.encode(json.getString("passwd")));
    }
    baseMapper.update(null, luw);
    responseJson.ok();
    responseJson.setData("更新成功");
    return responseJson;
  }

}
