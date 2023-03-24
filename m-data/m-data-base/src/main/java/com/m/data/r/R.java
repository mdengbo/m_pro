package com.m.data.r;

import com.m.data.page.BasePageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 统一返回值
 * */
@Data
@Accessors(chain = true) //支持链式编程
@NoArgsConstructor
@AllArgsConstructor
public class R<T> extends BasePageInfo {

    /**
     * 响应码
     * */
    private Integer code;

    /**
     * 响应描述
     * */
    private String msg;

    /**
     * 返回体
     * */
    private T data;

}
