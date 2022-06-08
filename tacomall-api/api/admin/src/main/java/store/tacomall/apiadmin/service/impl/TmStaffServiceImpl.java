/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-23 10:08:13
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/service/impl/TmStaffServiceImpl.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;

import store.tacomall.apiadmin.service.TmStaffService;
import store.tacomall.apiadmin.valid.tm.TmStaffAddValid;
import store.tacomall.common.entity.tm.TmAccessRule;
import store.tacomall.common.entity.tm.TmDept;
import store.tacomall.common.entity.tm.TmJob;
import store.tacomall.common.entity.tm.TmStaff;
import store.tacomall.common.json.ResponseJson;
import store.tacomall.common.json.ResponsePageJson;
import store.tacomall.common.mapper.tm.TmAccessRuleMapper;
import store.tacomall.common.mapper.tm.TmDeptMapper;
import store.tacomall.common.mapper.tm.TmJobMapper;
import store.tacomall.common.mapper.tm.TmStaffMapper;
import store.tacomall.common.util.PasswordUtil;
import store.tacomall.common.util.StringUtil;
import store.tacomall.common.vo.base.PageVo;

@Service
public class TmStaffServiceImpl extends ServiceImpl<TmStaffMapper, TmStaff> implements TmStaffService {

    @Autowired
    private TmAccessRuleMapper adminAuthRuleMapper;

    @Autowired
    private TmDeptMapper tmDeptMapper;

    @Autowired
    private TmJobMapper tmJobMapper;

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
    public ResponseJson<TmStaff> info(Integer id) {
        ResponseJson<TmStaff> responseJson = new ResponseJson<>();
        if (id.equals(0)) {
            TmStaff tmStaff = (TmStaff) SecurityUtils.getSubject().getPrincipal();
            id = tmStaff.getId();
        }
        responseJson.setData(baseMapper.queryInfo(new QueryWrapper<TmStaff>().lambda().eq(TmStaff::getId, id)));
        responseJson.ok();
        return responseJson;
    }

    @Override
    public ResponseJson<List<TmAccessRule>> accessRuleList() {
        ResponseJson<List<TmAccessRule>> responseJson = new ResponseJson<>();
        TmStaff tmStaff = (TmStaff) SecurityUtils.getSubject().getPrincipal();
        TmDept tmDept = tmDeptMapper.selectById(tmStaff.getDeptId());
        TmJob tmJob = tmJobMapper.selectById(tmStaff.getJobId());
        responseJson.setData(new ArrayList<>());
        if (ObjectUtil.isNotNull(tmDept) && ObjectUtil.isNotEmpty(tmDept.getAccessRuleIds())) {
            responseJson.setData(
                    adminAuthRuleMapper.selectList(new QueryWrapper<TmAccessRule>().lambda().in(TmAccessRule::getId,
                            Arrays.asList(tmDept.getAccessRuleIds().split(",")).stream().filter((String s) -> {
                                if (ObjectUtil.isNotNull(tmJob) && ObjectUtil.isNotNull(tmJob.getIgnoreAccessRuleIds())
                                        && Arrays.asList(tmJob.getIgnoreAccessRuleIds().split(",")).contains(s)) {
                                    return false;
                                }
                                return true;
                            }).collect(Collectors.toList()))));
        }
        responseJson.ok();
        return responseJson;
    }

    @Override
    public ResponsePageJson<List<PageVo>> page(Integer pageIndex, Integer pageSize, JSONObject json) {
        ResponsePageJson<List<PageVo>> responsePageVo = new ResponsePageJson<>();
        Page<PageVo> page = new Page<>(pageIndex, pageSize);
        QueryWrapper<TmStaff> qw = new QueryWrapper<TmStaff>();

        JSONObject query = json.getJSONObject("query");
        if (ObjectUtil.isNotEmpty(query)) {
            if (StringUtil.isNotBlank(query.getString("keyword"))) {
                qw.like("ts.nickname", query.getString("keyword"));
            }
            if (ObjectUtil.isNotEmpty(query.getInteger("deptId"))) {
                qw.eq("ts.dept_id", query.getInteger("deptId"));
            }
        }
        qw.eq("ts.is_delete", 0);
        IPage<PageVo> result = baseMapper.queryPage(page, qw);
        responsePageVo.setData(result.getRecords());
        responsePageVo.buildPage(result.getCurrent(), result.getSize(), result.getTotal());
        responsePageVo.ok();
        return responsePageVo;
    }

    @Override
    public ResponseJson<TmStaff> add(TmStaffAddValid json) {
        ResponseJson<TmStaff> responseJson = new ResponseJson<>();
        responseJson.setStatus(false);
        TmStaff tmStaff = JSONObject.parseObject(JSONObject.toJSONString(json), TmStaff.class);
        tmStaff.setPasswd(PasswordUtil.encode(tmStaff.getPasswd()));
        baseMapper.insert(tmStaff);
        responseJson.ok();
        responseJson.setData(tmStaff);
        return responseJson;
    }

    @Override
    public ResponseJson<String> update(JSONObject json) {
        ResponseJson<String> responseJson = new ResponseJson<>();
        TmStaff tmStaff = JSON.toJavaObject(json, TmStaff.class);
        if (StringUtil.isNotBlank(tmStaff.getPasswd())) {
            tmStaff.setPasswd(PasswordUtil.encode(tmStaff.getPasswd()));
        }
        baseMapper.updateById(tmStaff);
        responseJson.setData("更新成功");
        responseJson.ok();
        return responseJson;
    }

}
