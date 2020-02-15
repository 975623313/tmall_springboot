package com.lizhifeng.tmall.realm;

import com.lizhifeng.tmall.pojo.Admin;
import com.lizhifeng.tmall.service.AdminService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/25 23:01
 */
public class AdminJPARealm extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("认证通过");
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String adminName = token.getPrincipal().toString();
        Admin admin = adminService.getByAdminname(adminName);
        String adminPasswordInDB = admin.getAdminpwd();
        String salt = admin.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(adminName, adminPasswordInDB, ByteSource.Util.bytes(salt),
                getName());
        return authenticationInfo;
    }
}
