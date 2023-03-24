package com.m.commons.mysql.plugin;

import com.m.commons.mysql.utils.MybatisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Statement;

/**
 * sql用户自定义拦截器，针对sql做增强处理
 * Executor sql的内部执行器
 * ParameterHandler 拦截参数的处理
 * StatementHandler 拦截sql的构建
 * ResultSetHandler 拦截结果的处理
 * */

@Intercepts({
        //查找
        @Signature(
                type = StatementHandler.class, //指定拦截器增强对额内置对象的类型
                method = "query",//指定方法
                args = {Statement.class, ResultHandler.class}//上述方法的参数
        ),
        //更新增强
        @Signature(
                type = StatementHandler.class, //指定拦截器增强对额内置对象的类型
                method = "update",//指定方法
                args = {Statement.class}//上述方法的参数
        )
})
@Slf4j
public class SQLPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //记录当前执行的sql语句及耗时时间
        StatementHandler statementHandler = (StatementHandler) MybatisUtils.getNoProxyObj(invocation.getTarget());//获取拦截对象(及上述注解中的 type 类)
        String sql = statementHandler.getBoundSql().getSql();

        //记录执行的sql
        log.debug("[SQL - EXEU] 执行的sql:{}", sql);

        //记录耗时时间
        long begin = System.currentTimeMillis();
        //执行
        Object proceed = invocation.proceed();

        long end = System.currentTimeMillis();
        log.debug("[SQL -  EXEU] sql耗时：{}", BigDecimal.valueOf(end).subtract(BigDecimal.valueOf(begin))
                                                .divide(BigDecimal.valueOf(1000))
                                                .setScale(6, RoundingMode.DOWN));

        return proceed;
    }
}
