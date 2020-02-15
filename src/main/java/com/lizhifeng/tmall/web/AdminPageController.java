package com.lizhifeng.tmall.web;

import com.lizhifeng.tmall.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 16:54
 */
@Controller
public class AdminPageController {


    @Autowired
    private UserService userService;




    @GetMapping(value="/superedit")
    public String editAdmin(){

        return "admin/editAdmin";

    }


    @RequestMapping("/registerAdminPage")
    public String registerAdminPage(){
        return "admin/addAdmin";
    }
    @GetMapping("/adminlogout")
    public String logout( ) {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
            subject.logout();
        return "redirect:admin_login";
    }

    @RequestMapping("/erro")
    public String erroPage(){
        return "admin/erro";
    }

    @RequestMapping(value = "super_login")
    public String superLoginPage(){
        return "admin/superLogin";
    }
    @RequestMapping(value = "superIndex")
    public String superIndexPage(){
        return "admin/superIndex";
    }

    @GetMapping(value = "/admin_login")
    public String adminlogin(){
        return "admin/adminLogin";
    }

    @GetMapping(value="/admin")
    public String admin(){
        return "redirect:admin_category_list";
    }

    @GetMapping(value="/admin_category_list")
    public String listCategory(){
        return "admin/listCategory";
    }

    @GetMapping(value="/admin_category_edit")
    public String editCategory(){
        return "admin/editCategory";

    }

    @GetMapping(value="/admin_order_list")
    public String listOrder(){
        return "admin/listOrder";

    }

    @GetMapping(value="/admin_product_list")
    public String listProduct(){
        return "admin/listProduct";

    }

    @GetMapping(value="/admin_product_edit")
    public String editProduct(){
        return "admin/editProduct";

    }
    @GetMapping(value="/admin_productImage_list")
    public String listProductImage(){
        return "admin/listProductImage";

    }

    @GetMapping(value="/admin_property_list")
    public String listProperty(){
        return "admin/listProperty";

    }

    @GetMapping(value="/admin_property_edit")
    public String editProperty(){

        return "admin/editProperty";

    }

    @GetMapping(value="/admin_propertyValue_edit")
    public String editPropertyValue(){
        return "admin/editPropertyValue";

    }

    @GetMapping(value="/admin_user_list")
    public String listUser(){
        return "admin/listUser";

    }




}
