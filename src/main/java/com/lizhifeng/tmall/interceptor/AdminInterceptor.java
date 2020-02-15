package com.lizhifeng.tmall.interceptor;

import com.lizhifeng.tmall.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/25 22:41
 */
public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");

        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "admin",
                "admin_category_list",
                "admin_category_edit",
                "admin_order_list",
                "admin_product_list",
                "admin_product_edit",
                "admin_property_list",

                "admin_property_edit",
                "admin_propertyValue_edit",
                "admin_user_list",


        };
        String uri = httpServletRequest.getRequestURI();

        uri = StringUtils.remove(uri, contextPath+"/");

        String page = uri;



        if("/admin_login".equals(uri)||"admin_login".equals(page)||"/adminlogin".equals(uri)||"adminlogin".equals(page)){
            return true;
        }
        if(begingWith(page, requireAuthPages)){
            if(user!=null&& User.class.isInstance(user)){
                httpServletResponse.sendRedirect("erro");
                return false;
            }
            Subject subject = SecurityUtils.getSubject();
            if(!subject.isAuthenticated()) {


                httpServletResponse.sendRedirect("admin_login");
                return false;
            }
        }

        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {

        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }

        return result;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
