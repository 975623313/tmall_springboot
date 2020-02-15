package com.lizhifeng.tmall.interceptor;



/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 16:09
 */

import com.lizhifeng.tmall.pojo.Category;
import com.lizhifeng.tmall.pojo.OrderItem;
import com.lizhifeng.tmall.pojo.User;
import com.lizhifeng.tmall.service.CategoryService;
import com.lizhifeng.tmall.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;


public class OtherInterceptor implements HandlerInterceptor {
    @Autowired
    CategoryService categoryService;
    @Autowired
    OrderItemService orderItemService;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        User user=null;
        HttpSession session = httpServletRequest.getSession();
        Object object =session.getAttribute("user");
        if (object!=null){
            if(User.class.isInstance(object)){
                user= (User) object;
                int  cartTotalItemNumber = 0;
                if(null!=user) {
                    List<OrderItem> ois = orderItemService.listByUser(user);
                    for (OrderItem oi : ois) {
                        cartTotalItemNumber+=oi.getNumber();
                    }

                }

                List<Category> cs =this.categoryService.list();
                String contextPath=httpServletRequest.getServletContext().getContextPath();

                httpServletRequest.getServletContext().setAttribute("categories_below_search", cs);
                session.setAttribute("cartTotalItemNumber", cartTotalItemNumber);
                httpServletRequest.getServletContext().setAttribute("contextPath", contextPath);
            }



    }
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}