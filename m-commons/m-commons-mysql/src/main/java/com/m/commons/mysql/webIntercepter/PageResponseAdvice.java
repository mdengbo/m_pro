package com.m.commons.mysql.webIntercepter;

import com.m.commons.mysql.utils.MPageUtils;
import com.m.data.page.Page;
import com.m.data.r.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一处理响应信息
 * */
@Slf4j
@RestControllerAdvice
public class PageResponseAdvice implements ResponseBodyAdvice<R> {

    /**
     * supports 返回 true 执行 beforeBodyWrite 方法
     * */
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return methodParameter.getMethod().getReturnType() == R.class;
    }

    @Override
    public R beforeBodyWrite(R body, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        //获取 threadLocal 分页对象
        Page page = MPageUtils.getPage();
        if (null != page) {
            body.setPage(page);
        }

        log.debug("[PAGE PageResponseAdvice] 返回值：{}", body);
        return body;
    }

}
