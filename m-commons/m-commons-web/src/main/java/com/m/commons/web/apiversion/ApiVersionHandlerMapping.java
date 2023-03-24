package com.m.commons.web.apiversion;

import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

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
        return super.getCustomTypeCondition(handlerType);
    }

    /**
     * spring 容器初始化时触发
     * @param method Controller 中对应方法
     * @return 返回自定义的方法请求信息
     * */
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        return super.getCustomMethodCondition(method);
    }
}
