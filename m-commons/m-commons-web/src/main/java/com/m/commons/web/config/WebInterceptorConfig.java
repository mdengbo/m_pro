package com.m.commons.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.util.List;

/**
 * web 拦截器
 * */
public class WebInterceptorConfig implements WebMvcConfigurer {

    /**
     * 取出spring容器中所有的拦截器类型进行注册,预先注册管理
     * eg: 一般所有自定义拦截起 都继承HandlerInterceptorAdapter
     * */
    @Autowired(required = false)
    private List<HandlerInterceptorAdapter> interceptorAdapterList;

    /**
     * 拦截器
     * */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        if (!CollectionUtils.isEmpty(interceptorAdapterList)) {
            interceptorAdapterList.forEach(registry::addInterceptor);
        }
    }
}
