package com.m.data.r;

import lombok.Getter;

/**
 * 统一响应码处理
 * */
@Getter
public enum CodesEnums {

    SUCC(200, "交易成功"),
    FAIL(500, "交易失败"),
    PARAM_ERROR(501, "参数校验异常"),
    ;

    private  Integer code;

    private String msg;

    CodesEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
