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
 * @time: 2019/12/25 22:38
 */
@RestController
public class AdminRESTController {

    @Autowired
    private SuperService superService;
    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;


//    @PostMapping("/superLogin")
//    public Object superLogin(@RequestBody User userParam, HttpSession session) {
////        System.out.println("------------------------"+userParam);
//        String name =  userParam.getName();
//        name = HtmlUtils.htmlEscape(name);
//
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(name, userParam.getPassword());
//        try {
//            subject.login(token);
//            User user = userService.getByName(name);
//            boolean flag=userParam.getSign()==user.getSign();
//            if (flag){
////	    	subject.getSession().setAttribute("user", user);
//                session.setAttribute("user", user);
//                return Result.success();
//            }
//            else {
//                String message ="账号密码错误";
//                return Result.fail(message);
//
//            }
//        } catch (AuthenticationException e) {
//            String message ="账号密码错误";
//            return Result.fail(message);
//        }
//
//    }

    @PostMapping("/super_AddAdmin")
    public Object register(@RequestBody User user) {

        System.out.println("foreregister----"+user);
        String name =  user.getName();
        String password = user.getPassword();
        name = HtmlUtils.htmlEscape(name);
        user.setName(name);

        boolean exist = false;
        if (userService.isExist(name)){
            exist=true;
        }
        System.out.println("-----exist---"+exist);
        if(exist){
            String message ="用户名已经被使用,不能使用";
            return Result.fail(message);
        }

        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        String algorithmName = "md5";

        String encodedPassword = new SimpleHash(algorithmName, password, salt, times).toString();

        user.setSalt(salt);
        user.setPassword(encodedPassword);

        userService.add(user);

        return Result.success();
    }

    @GetMapping("/supers")
    public Page4Navigator<User> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size,HttpSession session) throws Exception {

        start = start<0?0:start;
        User user = (User) session.getAttribute("user");
        Page4Navigator<User> page = this.userService.listBySign(start,size,5,user.getSign());
        return page;
    }
    @DeleteMapping("/supers/{id}")
    public String delete(@PathVariable("id") int id, HttpServletRequest request)  throws Exception {
        System.out.println("delete---id"+id);
        userService.delete(id);
        return null;
    }


    //通过id得到用户信息
    @GetMapping("/super_GetEditAdmin/{id}")
    public User get(@PathVariable("id") int id) throws Exception {
        User bean=userService.get(id);
        System.out.println(bean);
        return bean;
    }
    //更新用户
    @PutMapping("/super_updateAdmin")
    public Object update(@RequestBody User bean) throws Exception {
        System.out.println("updateAdmin--------"+bean);
        userService.update(bean);
        return bean;
    }





//
//
//    @Autowired
//    private AdminService adminService;
//
//    @PostMapping("/adminlogin")
//    public Object adminLogin(@RequestBody Admin adminParam, HttpSession session) {
//
//
//
//        String name =  adminParam.getName();
//        name = HtmlUtils.htmlEscape(name);
//
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
//
//    }



}
