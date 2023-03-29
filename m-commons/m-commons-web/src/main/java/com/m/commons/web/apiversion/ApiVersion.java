package com.m.commons.web.apiversion;

import java.lang.annotation.*;

/**
 * web 多版本控制
 * */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    //版本号
    double value();

    //多系统标识
    PlatFormTypeEnum srcType () default PlatFormTypeEnum.ALL;


}
