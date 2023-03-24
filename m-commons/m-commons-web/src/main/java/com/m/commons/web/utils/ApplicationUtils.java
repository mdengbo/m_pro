package com.m.commons.web.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * spring utils
 * */
public class ApplicationUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    public static <T> T getBean(Class<T> tcls) {
        return applicationContext.getBean(tcls);
    }

    /**
     * 如果某个类实现了ApplicationContextAware接口，会在类初始化完成后调用setApplicationContext（）方法进行操作
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      ApplicationUtils.applicationContext = applicationContext;
    }
}
