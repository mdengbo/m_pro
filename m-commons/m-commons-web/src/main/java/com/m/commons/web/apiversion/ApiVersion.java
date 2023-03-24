package com.m.commons.web.apiversion;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * web 多版本控制
 * */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ApiVersion {

    //版本号
    double value();
}
