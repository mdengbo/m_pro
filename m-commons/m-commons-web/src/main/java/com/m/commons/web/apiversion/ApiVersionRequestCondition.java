package com.m.commons.web.apiversion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class ApiVersionRequestCondition implements RequestCondition<ApiVersionRequestCondition> {

    /**
     * 默认版本号
     * */
    private final double apiVersion;

    /**
     * 默认系统来源信息
     * */
    private final PlatFormTypeEnum platForm;

    public double getApiVersion() {
        return apiVersion;
    }

    public PlatFormTypeEnum getPlatForm() {
        return platForm;
    }

    private static final String VERSION_NAME = "api-version";
    //系统来源信息
    private static final String PLAT_FORM = "plat-form";

    public ApiVersionRequestCondition(double apiVersion, PlatFormTypeEnum platForm) {
        this.apiVersion = apiVersion;
        this.platForm = platForm;

    }

    /**
     * 客户端发送请求时触发
     * 当 class类上的条件和 method上的条件冲突时，如何处理
    * */
    @Override
    public ApiVersionRequestCondition combine(ApiVersionRequestCondition apiVersionRequestCondition) {

        return apiVersionRequestCondition;
    }

    /**
     * 客户端发送请求时触发
     * 根据请求返回当前 requestCondition 符合条件的请求对象，不符合返回空
     * // 这个是重点，用于判断当前匹配条件和请求是否匹配；如果不匹配返回null
     * 	// 如果匹配，生成一个新的请求匹配条件，该新的请求匹配条件是当前请求匹配条件针对指定请求request的剪裁
     * 	// 举个例子来讲，如果当前请求匹配条件是一个路径匹配条件，包含多个路径匹配模板，
     * 	// 并且其中有些模板和指定请求request匹配，那么返回的新建的请求匹配条件将仅仅
     * 	// 包含和指定请求request匹配的那些路径模板。
     * */
    @Override
    public ApiVersionRequestCondition getMatchingCondition(HttpServletRequest httpServletRequest) {

        //获取请求头中的版本信息，默认 1.0
        String reqVersion = httpServletRequest.getHeader(VERSION_NAME);

        //获取请求头中的多系统信息，默认 pc
        String reqPlatFrom = httpServletRequest.getHeader(PLAT_FORM);

        if (StringUtils.isEmpty(reqVersion)) {
            //在从请求参数中获取
            reqVersion = httpServletRequest.getParameter(VERSION_NAME);
        }

        if (StringUtils.isEmpty(reqPlatFrom)) {
            //在从请求参数中获取
            reqPlatFrom = httpServletRequest.getParameter(PLAT_FORM);
        }

        //判断是否为空
        if (StringUtils.isEmpty(reqVersion)) {
            reqVersion = "1.0";
        }

        //判断是否为空
        if (StringUtils.isEmpty(reqPlatFrom)) {
            reqPlatFrom = PlatFormTypeEnum.ALL.getCode();
        }

        //和当前条件的版本号对比
        //支持服务端接口向上兼容 即： 2.0的接口支持 2.0+ 的客户端请求
        double exchangeApiVersion;
        try {
            //当输入值小于 1.0 取1.0
            exchangeApiVersion = Math.max(Double.parseDouble(reqVersion), 1.0);
        }catch (Exception e) {
            //转换异常 避免非法输入
            log.error("【API HANDLER】前段出入参数有误（只能为数字），前段传入值：{},现返回默认版本号【{}】", apiVersion, String.valueOf(apiVersion));
            exchangeApiVersion = apiVersion;
        }

        log.info("【API HANDLER】调入版本号信息reqVersion: {}, 调入系统 reqPlatFrom:{}", reqVersion, reqPlatFrom);
        if (this.apiVersion <= exchangeApiVersion && reqPlatFrom.equals(this.platForm.getCode())) {
            //匹配成功 返回当前条件
            return this;
        }

        //无匹配的返回 null,证明该系统无对应的版本请求
        return null;
    }

    /**
     * 客户端发送请求时触发
     * 排除：选择最优的条件匹配对象
    * */
    @Override
    public int compareTo(ApiVersionRequestCondition apiVersionRequestCondition, HttpServletRequest httpServletRequest) {

        //向上兼容时，让服务端版本号较大的服务接口 接收处理请求信息
        return Double.compare(apiVersionRequestCondition.getApiVersion(), this.apiVersion);
    }
}
