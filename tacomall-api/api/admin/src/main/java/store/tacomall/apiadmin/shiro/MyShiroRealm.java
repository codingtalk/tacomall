/***
 * @Author: 码上talk|RC
 * @Date: 2020-06-09 23:20:41
 * @LastEditTime: 2021-10-19 22:05:10
 * @LastEditors: 码上talk|RC
 * @Description: 
 * @FilePath: /tacomall-api/api/admin/src/main/java/store/tacomall/apiadmin/shiro/MyShiroRealm.java
 * @Just do what I think it is right
 */
package store.tacomall.apiadmin.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.hutool.core.util.ObjectUtil;
import store.tacomall.common.entity.tm.TmStaff;
import store.tacomall.common.mapper.tm.TmStaffMapper;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private TmStaffMapper TmStaffMapper;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        TmStaff TmStaff = (TmStaff) principal.getPrimaryPrincipal();
        try {
            authorizationInfo.addRole(String.valueOf(TmStaff.getJobId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        TmStaff tmStaff = TmStaffMapper
                .selectOne(new QueryWrapper<TmStaff>().lambda().eq(TmStaff::getUsername, username));
        if (ObjectUtil.isNull(tmStaff)) {
            throw new UnknownAccountException();
        }
        if (tmStaff.getStatus() == 0) {
            throw new LockedAccountException();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(tmStaff, tmStaff.getPasswd(),
                getName());
        return authenticationInfo;
    }
}
