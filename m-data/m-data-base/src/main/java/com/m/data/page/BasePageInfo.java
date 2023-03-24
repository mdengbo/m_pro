package com.m.data.page;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回page 实体
 * */
@Data
public class BasePageInfo implements Serializable {
    private Page page;
}
