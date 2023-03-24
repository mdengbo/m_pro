package com.m.commons.mysql.webIntercepter;


import com.m.commons.mysql.utils.MPageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * servlet拦截处理器
 * */
@Slf4j
public class PageInterceptor extends HandlerInterceptorAdapter {

    /**
     * 前置拦截器
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String pageNum = request.getParameter("pageNum");
        String pageSize = request.getParameter("pageSize");
        //设置分页参数
        if(!StringUtils.isEmpty(pageNum) && !StringUtils.isEmpty(pageSize)) {
            try {

                MPageUtils.setPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            }catch (Exception e) {
                log.error("[PAGE INTERCEPTOR] 分页参数设置失败，传入值pageNum：{}， pageSize：{}", pageNum, pageSize);
                throw new RuntimeException("【PageInterceptor】拦截器管理分页参数处理异常：" + e.getMessage());
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("处理完成，清空分页设置参数");
        MPageUtils.clear();
    }
}
