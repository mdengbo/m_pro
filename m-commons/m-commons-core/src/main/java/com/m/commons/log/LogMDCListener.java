package com.m.commons.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

/**
 * 通过： spring.factories 自定义监听处理
 * 日志監聽事件預處理 以便log4j2.xml 讀取配置文件信息
 *
 * */
@Slf4j
public class LogMDCListener implements GenericApplicationListener {
    /**
     * 所有环境配置信息会汇总在  configrationProperties内
     * */
    private static final String APPLICATION_CONFIG_PROPERTIES = "configurationProperties";
    private static final String SPRING_APPLICATION_NAME = "spring.application.name";
    private static final String LOGGING_LEVEL = "m.log4j2.level";

    /**
     * 監聽所有事件類型，如果返回true 則用 onApplicationEvent 方法，進行事件處理
     *
     * */
    @Override
    public boolean supportsEventType(ResolvableType resolvableType) {

        return ApplicationEnvironmentPreparedEvent.class == resolvableType.getRawClass();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {



        ApplicationEnvironmentPreparedEvent evet = (ApplicationEnvironmentPreparedEvent) applicationEvent;
        //获取当前环境参数
        ConfigurableEnvironment environment = evet.getEnvironment();
        MutablePropertySources prop = environment.getPropertySources();
        prop.stream().forEach(obj->{
            System.out.println("配置信息：" + obj);
        });
        PropertySource<?> propertySource = prop.get(APPLICATION_CONFIG_PROPERTIES);

        String appName = (String)propertySource.getProperty(SPRING_APPLICATION_NAME);
        String logLevel = (String)propertySource.getProperty(LOGGING_LEVEL);
        log.info("日志监听事件，服务名:{}", appName);
        log.info("日志监听事件，日志级别:{}", logLevel);
        //配置日志名称
        MDC.put("logName", appName);
        //以服务=名做日志路径
        MDC.put("logPath", appName);
        //以服务=名做日志路径
        MDC.put("logLevel", logLevel);
    }

    /**
     * 设置优先级 比 日志处理类监听器的优先级高以便后续日志文件能读取到对应的参数
     * LoggingApplicationListener
     * */
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
