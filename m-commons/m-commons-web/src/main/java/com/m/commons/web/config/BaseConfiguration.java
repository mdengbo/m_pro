package com.m.commons.web.config;

import com.m.commons.web.exception.GlobalException;
import com.m.commons.web.utils.ApplicationUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web 自动装配配置文件
 * */
@Configuration
public class BaseConfiguration {


    /**
     * 装配全局异常处理类， 便于spring管理（类似 @Component @Service）
     * */
    @Bean
    public GlobalException getGlobalException() {
        return new GlobalException();
    }

    /**
     * 装配 自定义工具
     * 装配的目的： 触发 ApplicationUtils 中属性初始化
     * */
    @Bean
    public ApplicationUtils getApplicationUtils() {
        return new ApplicationUtils();
    }

    /**
     * 注册web 拦截器
     * */
    @Bean
    public WebInterceptorConfig getWebInterceptorConfig() {
        return new WebInterceptorConfig();
    }
}
