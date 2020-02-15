package com.lizhifeng.tmall.web;

import com.lizhifeng.tmall.pojo.Admin;
import com.lizhifeng.tmall.service.AdminService;
import com.lizhifeng.tmall.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/25 22:38
 */
@RestController
public class AdminRESTController {


    @Autowired
    private AdminService adminService;

    @PostMapping("/adminlogin")
    public Object adminLogin(@RequestBody Admin adminParam, HttpSession session) {



        String name =  adminParam.getName();
        name = HtmlUtils.htmlEscape(name);

        Subject subject = SecurityUtils.getSubject();
        new UsernamePasswordToken();
        UsernamePasswordToken token = new UsernamePasswordToken(name, adminParam.getAdminpwd(),"admin");
        try {
            subject.login(token);
            Admin admin = adminService.getByAdminname(name);
//	    	subject.getSession().setAttribute("user", user);
            session.setAttribute("user", admin);
            return Result.success();
        } catch (AuthenticationException e) {
            String message ="账号密码错误";
            return Result.fail(message);
        }

    }



}
