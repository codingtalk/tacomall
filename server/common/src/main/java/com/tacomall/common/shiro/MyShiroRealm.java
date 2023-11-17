
package com.tacomall.common.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tacomall.common.entity.org.OrgStaff;
import com.tacomall.common.mapper.org.OrgStaffMapper;

import cn.hutool.core.util.ObjectUtil;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    Environment environment;

    @Autowired
    OrgStaffMapper OrgStaffMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        OrgStaff OrgStaff = (OrgStaff) principal.getPrimaryPrincipal();
        try {
            authorizationInfo.addRole(String.valueOf(OrgStaff.getJobId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        OrgStaff orgStaff = OrgStaffMapper
                .selectOne(new QueryWrapper<OrgStaff>().lambda()
                        .eq(OrgStaff::getUsername, username));
        if (ObjectUtil.isNull(orgStaff)) {
            throw new UnknownAccountException();
        }
        if (orgStaff.getStatus() == 0) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(orgStaff, orgStaff.getPasswd(),
                getName());
        return authenticationInfo;
    }
}
