package com.m.commons.web.feign;

import com.m.commons.web.utils.RequestUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description feign拦截器  配合： @EnableFeignClients 注解使用
 *      可在 wfw 间通过 feign 调用时，自定义一些参数传递
 *      eg: 比如 自定义的版本号 或者  token 信息
 *
 * @Author Mdengbo
 * @Date 2023/3/30 下午3:19
 * @Version 1.0
 */

public class FeignParamInterceptor implements RequestInterceptor {

    /**
     * 请求参数列表,通过配置信息注入
     * */
    @Autowired
    private FeignParamsProperties feignParamsProperties;

    @Override
    public void apply(RequestTemplate requestTemplate) {

        if(null == feignParamsProperties || CollectionUtils.isEmpty(feignParamsProperties.getParamsName())) return;


        //获取 request 请求参数
        HttpServletRequest httpServletRequest = RequestUtils.getHttpServletRequest();

        for (String paramName : feignParamsProperties.getParamsName()) {

            //获取请求头上的参数 至于请求头上
            String headerValue = httpServletRequest.getHeader(paramName);
            if (!StringUtils.isEmpty(headerValue)) {

                requestTemplate.header(paramName, headerValue);
            } else {
                //若请求头上无参数值时，从参数列表上再获取一次，并至于请求参数上
                String parameterValue = httpServletRequest.getParameter(paramName);
                if (!StringUtils.isEmpty(parameterValue)) {
                    requestTemplate.query(paramName, parameterValue);
                }
            }
        }

    }
}
