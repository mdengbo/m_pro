package com.m.commons.web.apiversion;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 多版本处理器
 * */
public class ApiVersionHandlerMapping extends RequestMappingHandlerMapping {

    /**
    * spring 容器初始化时触发
     *
     * 工程中所有的 Controller 会调用此方法
     * @param handlerType Controller 对象，
     * @return 返回开发自定义的请求条件信息
     *
    * */
    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {

        //获取版本信息
        ApiVersion apiVersion = AnnotationUtils.getAnnotation(handlerType, ApiVersion.class);

        if(Objects.isNull(apiVersion)) {
            return new ApiVersionRequestCondition(1.0, PlatFormTypeEnum.ALL);
        }

        //将注解上的版本号信息设置到条件对象
        //当版本号信息为空时  设置默认版本号信息
        return new ApiVersionRequestCondition(apiVersion.value(), apiVersion.srcType());
    }

    /**
     * spring 容器初始化时触发
     * @param method Controller 中对应方法
     * @return 返回自定义的方法请求信息
     * */
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {

        //获取版本信息
        ApiVersion apiVersion = AnnotationUtils.getAnnotation(method, ApiVersion.class);

        //若方法上的版本号信息为空 再尝试获取类上的版本信息
        if (null == apiVersion) {
            //获取类上版本
            apiVersion = AnnotationUtils.getAnnotation(method.getDeclaringClass(), ApiVersion.class);
        }
        //将注解上的版本号信息设置到条件对象
        //当版本号信息为空时  设置默认版本号信息
        if (Objects.isNull(apiVersion)) {
            return new ApiVersionRequestCondition(1.0, PlatFormTypeEnum.ALL);
        }

        return new ApiVersionRequestCondition(apiVersion.value(), apiVersion.srcType());
    }
}
