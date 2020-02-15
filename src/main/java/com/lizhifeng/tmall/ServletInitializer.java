package com.lizhifeng.tmall;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @description:
 * @author: 李志峰
 * @time: 2020/1/2 21:54
 */
public class ServletInitializer extends SpringBootServletInitializer {
    public ServletInitializer() {
        System.out.println("初始化ServletInitializer");
    }

    @Override
     protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);//MyApplication是启动类名
         }
}

