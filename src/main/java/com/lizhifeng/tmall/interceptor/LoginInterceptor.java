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
 * @time: 2019/12/23 16:08
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute("user");

        String contextPath=session.getServletContext().getContextPath();
        String[] requireAuthPages = new String[]{
                "buy",
                "alipay",
                "payed",
                "cart",
                "bought",
                "confirmPay",
                "orderConfirmed",
                "forebuyone",
                "forebuy",
                "foreaddCart",
                "forecart",
                "forechangeOrderItem",
                "foredeleteOrderItem",
                "forecreateOrder",
                "forepayed",
                "forebought",
                "foreconfirmPay",
                "foreorderConfirmed",
                "foredeleteOrder",
                "forereview",
                "foredoreview",
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

                "supers",
                "super_AddAdmin",
                "super_AdminEditPage",
                "super_updateAdmin",
                "super_GetEditAdmin",
                "super_IndexPage",
                "super_logout",
        };


        String uri = httpServletRequest.getRequestURI();

        uri = StringUtils.remove(uri, contextPath+"/");
        String page = uri;
        if("/admin_login".equals(uri)||"admin_login".equals(page)||"/adminlogin".equals(uri)||"adminlogin".equals(page)){
            System.out.println("hahahahhahah-----------------------------------------------------------------------------------");
            return true;
        }
        if("/superLogin".equals(uri)||"superLogin".equals(page)||"/super_loginPage".equals(uri)||"super_loginPage".equals(page)){
            System.out.println("hahahahhahah-----------132423453245------------------------------------------------------------------------");
            return true;
        }


        if(begingWith(page,requireAuthPages)){

            String substring = StringUtils.substring(page, 0, 5);
//            String substring = page.substring(0,5);

            boolean adminFlag="admin".equals(substring);

            boolean superFlag="super".equals(substring);

//            String[] superUri=new String[]{
//                    "registerAdmin",
//                    "supers",
//                    "editAdminsuper",
//                    "updateAdmin",
//                    "superIndex",
//                    "super_logout",
//            };
//            for (int i=0;i<superUri.length;i++){
//                if(superUri.equals(page)){
//                    superFlag=true;
//                }
//            }
            Subject subject = SecurityUtils.getSubject();
            if(!subject.isAuthenticated()) {
                if (adminFlag){
                    httpServletResponse.sendRedirect("admin_login");
                }else if(superFlag){
                    httpServletResponse.sendRedirect("super_loginPage");
                }


                else{
                    httpServletResponse.sendRedirect("login");
                }
                return false;
            }else {
                System.out.println("else ---subject----外面");
                if(adminFlag){
                   if (user.getSign()==0||user.getSign()==2){

                       httpServletResponse.sendRedirect("erro");
                       return false;
                   }
                }
                else if(superFlag){
                    if (user.getSign()==1||user.getSign()==0){
                        httpServletResponse.sendRedirect("erro");
                        return false;
                    }
                }
                else{
                    if (user.getSign()==1||user.getSign()==2){
                        httpServletResponse.sendRedirect("erro");
                        return false;
                    }
                }

//                }else if(user.getSign()==0){
//                    System.out.println("!(user.getSign()==0)");
//                    httpServletResponse.sendRedirect("erro");
//                    return false;
//                }
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
