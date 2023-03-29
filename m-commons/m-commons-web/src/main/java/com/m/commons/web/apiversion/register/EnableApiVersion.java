package com.m.commons.web.apiversion.register;


import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 开启多版本功能注解
 * */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ApiVersionRegister.class) //当类上有注解时，自动注册 ApiVersionRegister 到spring容器（类似与@Bean），当无该注解时，不注册 ApiVersionRegister
public @interface EnableApiVersion {
}
