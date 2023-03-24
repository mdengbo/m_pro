package com.m.commons.web.valid;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * 自定义校验性别格式
 * */
@Documented
@Target({ElementType.FIELD, ElementType.TYPE}) //可作用于属性和类上
@Retention(RetentionPolicy.RUNTIME) //运行时使用
//自定义校验注解  validatedBy表示由哪个具体校验类来实现校验
@Constraint(validatedBy= CustomValidHandler.class)
public @interface CustomValid {

    /**
     * 校验默认信息
     * */
    String message() default "校验未通过";

    /**
     * 校验组信息
     * */
    Class<?>[] groups() default {};

    /**
     * 校验负载  原检验
     * */
    Class<?>[] payload() default {};

    /**
     * 指定自定义的处理器 该处理器需要实现 MValid 接口
     * */
    Class<? extends MValid> hander();
}
