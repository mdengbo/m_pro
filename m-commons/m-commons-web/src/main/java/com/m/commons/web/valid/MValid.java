package com.m.commons.web.valid;

/**
 * 自定义校验接口
 * */
public interface MValid<T> {

    boolean isValid(CustomValid customValid, T data);
}
