package com.lizhifeng.tmall.interceptor;

import com.lizhifeng.tmall.pojo.Admin;
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
 * @time: 2020/1/2 15:11
 */
public class SuperInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("user");

        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "registerAdmin",
                "supers",
                "editAdminsuper",
                "updateAdmin",
                "superIndex",


        };
        String uri = httpServletRequest.getRequestURI();

        uri = StringUtils.remove(uri, contextPath+"/");

        String page = uri;



        if("/superLogin".equals(uri)||"superLogin".equals(page)||"/supper_login".equals(uri)||"supper_login".equals(page)){
            return true;
        }
        if(begingWith(page, requireAuthPages)){
            if(user!=null){
                if(User.class.isInstance(user)){
                  httpServletResponse.sendRedirect("super_login");
                  return false;
            }else if(Admin.class.isInstance(user)){
                    Admin admin = (Admin) user;
                    if (!"superAdmin".equals(admin.getSign())){
                        httpServletResponse.sendRedirect("super_login");
                        return false;

                    }
                }
            }

            Subject subject = SecurityUtils.getSubject();
            if(!subject.isAuthenticated()) {


                httpServletResponse.sendRedirect("super_login");
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
