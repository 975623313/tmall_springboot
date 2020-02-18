package com.lizhifeng.tmall.config;

import com.lizhifeng.tmall.interceptor.LoginInterceptor;
import com.lizhifeng.tmall.interceptor.OtherInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @description:
 * @author: 李志峰
 * @time: 2019/12/23 16:08
 */
@Configuration
class WebMvcConfigurer extends WebMvcConfigurerAdapter {

    @Bean
    public OtherInterceptor getOtherIntercepter() {
        return new OtherInterceptor();
    }
    @Bean
    public LoginInterceptor getLoginIntercepter() {
        return new LoginInterceptor();
    }
//    @Bean
//    public AdminInterceptor getAdminInterceptor() {
//        return new AdminInterceptor();
//    }
//
//    @Bean
//    public SuperInterceptor getSuperInterceptor(){
//        return new SuperInterceptor();
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(getOtherIntercepter())
                .addPathPatterns("/**");
        registry.addInterceptor(getLoginIntercepter())
                .addPathPatterns("/**");
//        registry.addInterceptor(getAdminInterceptor()).addPathPatterns("/**");
//        registry.addInterceptor(getSuperInterceptor()).addPathPatterns("/**");
    }
}

