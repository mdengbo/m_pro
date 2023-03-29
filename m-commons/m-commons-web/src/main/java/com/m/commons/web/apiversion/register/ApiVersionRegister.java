package com.m.commons.web.apiversion.register;

import com.m.commons.web.apiversion.ApiVersionHandlerMapping;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

public class ApiVersionRegister implements WebMvcRegistrations {


    /**
     * 注册返回自定义，替换掉SPringMvc 默认的 RequestMappingHandlerMapping
     * */

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {

        return new ApiVersionHandlerMapping();
    }
}
