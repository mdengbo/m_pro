package com.m.commons.web.apiversion;

public enum PlatFormTypeEnum {

    IOS("ios"),
    ANDROID("android"),
    PC("pc"),
    ALL("all"), //所有平台都能调用
    ;

    private String code;

    PlatFormTypeEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
