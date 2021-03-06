package com.lizhifeng.tmall.web;

import com.lizhifeng.tmall.util.Page4Navigator;
import com.lizhifeng.tmall.pojo.User;
import com.lizhifeng.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 16:51
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Page4Navigator<User> list(@RequestParam(value = "start", defaultValue = "0") int start, @RequestParam(value = "size", defaultValue = "5") int size, HttpSession session) throws Exception {
        start = start<0?0:start;
        User user = (User) session.getAttribute("user");
        Page4Navigator<User> page = userService.listBySign(start,size,5,user.getSign());
        return page;
    }




}
