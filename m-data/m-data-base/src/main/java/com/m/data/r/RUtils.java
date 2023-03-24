package com.m.data.r;
/**
 * 统一返回Utls
 * */
public class RUtils {

    /**
     * 成功
     * */
    public static R succ() {
        return new R(CodesEnums.SUCC.getCode(), CodesEnums.SUCC.getMsg(), null);
    }

    /**
     * 成功
     * */
    public static <T> R succ(T data) {
        return new R(CodesEnums.SUCC.getCode(), CodesEnums.SUCC.getMsg(), data);
    }

    /**
     * 失败
     * */
    public static R fail() {
        return new R(CodesEnums.FAIL.getCode(), CodesEnums.FAIL.getMsg(), null);
    }

    /**
     * 失败
     * */
    public static R fail(String msg) {
        return new R(CodesEnums.FAIL.getCode(), msg, null);
    }

    /**
     * 失败
     * */
    public static <T> R fail(T data) {
        return new R(CodesEnums.FAIL.getCode(), CodesEnums.FAIL.getMsg(), data);
    }

    /**
     * 自定义返回响应
     * */
    public static <T> R create(CodesEnums codes, T data) {
        return new R(codes.getCode(), codes.getMsg(), data);
    }

    /**
     * 不带返回体的响应
     * */
    public static R create(CodesEnums codes){
        return new R(codes.getCode(), codes.getMsg(), null);
    }


}
