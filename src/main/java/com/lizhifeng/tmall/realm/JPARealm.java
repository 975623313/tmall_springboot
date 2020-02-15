package com.lizhifeng.tmall.realm;

import com.lizhifeng.tmall.pojo.Admin;
import com.lizhifeng.tmall.pojo.User;
import com.lizhifeng.tmall.service.AdminService;
import com.lizhifeng.tmall.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 15:58
 */
public class JPARealm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        System.out.println("111111111111111111111------------12312-12312312");
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        return s;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println(token.toString()+"------67666666666666666666666");
        UsernamePasswordToken a= (UsernamePasswordToken) token;

        String passwordInDB=null;
        String salt=null;
        String userName = token.getPrincipal().toString();

        if ("admin".equals(a.getHost())){
             Admin admin = adminService.getByAdminname(userName);
             passwordInDB =admin.getAdminpwd();
             salt = admin.getSalt();
        }else{
            User user = userService.getByName(userName);
            passwordInDB = user.getPassword();
            salt = user.getSalt();
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userName, passwordInDB, ByteSource.Util.bytes(salt),
                getName());
        return authenticationInfo;
    }

//    @Override
//    public String getHost() {
//        return getHost();
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return null;
//    }
//
//    @Override
//    public Object getCredentials() {
//        return null;
//    }
}
