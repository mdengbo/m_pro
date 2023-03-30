package com.m.commons.web.config;

import com.m.commons.web.exception.GlobalException;
import com.m.commons.web.feign.FeignParamInterceptor;
import com.m.commons.web.feign.FeignParamsProperties;
import com.m.commons.web.utils.ApplicationUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * web 自动装配配置文件
 * */
@Configuration
public class BaseConfiguration {


    /**
     * @description
     * @author  Mdengbo
     * @date    2023/3/28 下午2:47
    */
    @Configuration
    public static class BaseAutoConfiguration {

        /**
         * 装配 自定义工具
         * 装配的目的： 触发 ApplicationUtils 中属性初始化
         * */
        @Bean
        public ApplicationUtils getApplicationUtils() {
            return new ApplicationUtils();
        }

    }

    /**
     * @description  Web容器相关的相关依赖注册
     * @author  Mdengbo
     * @date    2023/3/28 下午2:46
    */
    @Configuration
    public static class WebMvcConfiguration {

        /**
         * 装配全局异常处理类， 便于spring管理（类似 @Component @Service）
         * */
        @Bean
        public GlobalException getGlobalException() {
            return new GlobalException();
        }
        /**
         * 注册web 拦截器
         * */
        @Bean
        public WebInterceptorConfig getWebInterceptorConfig() {
            return new WebInterceptorConfig();
        }

        /**
         * 注册 自定义处理器
         * 注解 @EnableApiVersion 可实现bean 注册管理
         * */
        /*@Bean
        public WebMvcRegistrations getWebMvcRegistrations() {
            return new ApiVersionRegister();
        }*/
    }
    
    /**
     * @description  nacos 相关
     * @author  Mdengbo
     * @date    2023/3/28 下午2:49
    */
    @Configuration
    @EnableDiscoveryClient //启动nacos服务发现功能
    public static class NacosConfiguration {
        
    }


    /**
     * feign 依赖配置
     * */
    @Configuration
    @EnableConfigurationProperties(FeignParamsProperties.class) //自定义参数信息
    @EnableFeignClients(basePackages = "com.m.business.feign", defaultConfiguration = FeignParamInterceptor.class) //feign 接口位置  defaultConfiguration:  feign 拦截器管理
    public static class feignConfiguration {

    }
}
