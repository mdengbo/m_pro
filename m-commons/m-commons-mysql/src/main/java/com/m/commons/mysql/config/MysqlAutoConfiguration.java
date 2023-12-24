package com.m.commons.mysql.config;

import com.m.commons.mysql.plugin.PagePlugin;
import com.m.commons.mysql.plugin.SQLPlugin;
import com.m.commons.mysql.webIntercepter.PageInterceptor;
import com.m.commons.mysql.webIntercepter.PageResponseAdvice;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mysql 模块统一自定义扫描注册
 * */

@Configuration
@MapperScan("com.m.data.mapper") //mapper包扫描路径信息
@EnableTransactionManagement //开启事务管理功能
public class MysqlAutoConfiguration {

    @Bean
    //@ConditionalOnBean //当spring容器内加载bean时，才会加载该文件
    @ConditionalOnProperty(name = "my.prop.sql.enable", havingValue = "true", matchIfMissing = false) //基于配置文件信息
    public SQLPlugin getSQLPlugin() {

        return new SQLPlugin();
    }

    /**
     * 注册分页插件
     * */
    @Bean
    @ConditionalOnProperty(name = "my.prop.page.enable", havingValue = "true", matchIfMissing = true) //基于配置文件信息
    public PagePlugin getPagePlugin() {
        return new PagePlugin();
    }

    /**
     * 注册分页拦截器
     * */
    @Bean
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET) //当前是servlet 环境时加载
    @ConditionalOnProperty(name = "my.prop.page.enable", havingValue = "true", matchIfMissing = true) //基于配置文件信息
    public PageInterceptor getPageInterceptor() {
        return new PageInterceptor();
    }

    /**
     * 注册分页响应拦截器
     * */
    @Bean
    @ConditionalOnProperty(name = "my.prop.page.enable", havingValue = "true", matchIfMissing = true) //基于配置文件信息
    public PageResponseAdvice getPageResponseAdvice() {
        return new PageResponseAdvice();
    }
}
