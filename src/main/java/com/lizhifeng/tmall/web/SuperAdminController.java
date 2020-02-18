package com.lizhifeng.tmall.web;

import com.lizhifeng.tmall.pojo.Admin;
import com.lizhifeng.tmall.pojo.User;
import com.lizhifeng.tmall.service.AdminService;
import com.lizhifeng.tmall.service.SuperService;
import com.lizhifeng.tmall.service.UserService;
import com.lizhifeng.tmall.util.Page4Navigator;
import com.lizhifeng.tmall.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/27 0:41
 */
@RestController
public class SuperAdminController {

//    @Autowired
//    private SuperService superService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    AdminService adminService;
//
//    @PostMapping("/superLogin")
//    public Object adminLogin(@RequestBody Admin adminParam, HttpSession session) {
//        String name =  adminParam.getName();
//        name = HtmlUtils.htmlEscape(name);
//        System.out.println(name);
//        System.out.println(adminParam.getAdminpwd());
//        Subject subject = SecurityUtils.getSubject();
//        new UsernamePasswordToken();
//        UsernamePasswordToken token = new UsernamePasswordToken(name, adminParam.getAdminpwd(),"admin");
//        try {
//            subject.login(token);
//            Admin admin = adminService.getByAdminname(name);
////	    	subject.getSession().setAttribute("user", user);
//            session.setAttribute("user", admin);
//            return Result.success();
//        } catch (AuthenticationException e) {
//            String message ="账号密码错误";
//            return Result.fail(message);
//        }
//    }
//
//    @PostMapping("/registerAdmin")
//    public Object register(@RequestBody Admin admin) {
//
//        String name =  admin.getName();
//
//        String password = admin.getAdminpwd();
//        name = HtmlUtils.htmlEscape(name);
//        admin.setName(name);
//
//        boolean exist = false;
//        if (userService.isExist(name)&&superService.isExistAdmin(name)){
//            exist=true;
//        }
//
//        if(exist){
//            String message ="用户名已经被使用,不能使用";
//            return Result.fail(message);
//        }
//
//        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
//        int times = 2;
//        String algorithmName = "md5";
//
//        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();
//
//        admin.setSalt(salt);
//        admin.setAdminpwd(encodedPassword);
//        superService.add(admin);
//
//        return Result.success();
//    }
//
//    @GetMapping("/supers")
//    public Page4Navigator<User> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size) throws Exception {
//
//        start = start<0?0:start;
//        Page4Navigator<User> page = this.userService.listBySign(start,size,5);
//        return page;
//    }
//    @DeleteMapping("/supers/{id}")
//    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
//        System.out.println("delete---id"+id);
//        userService.delete(id);
//        return null;
//    }
//
//
//    //通过id得到用户信息
//    @GetMapping("/editAdminsuper/{id}")
//    public User get(@PathVariable("id") int id) throws Exception {
//        User bean=userService.get(id);
//        System.out.println(bean);
//        return bean;
//    }
//    //更新用户
//    @PutMapping("/updateAdmin")
//    public Object update(@RequestBody User bean) throws Exception {
//        System.out.println("updateAdmin--------"+bean);
//        userService.update(bean);
//        return bean;
//    }


}
