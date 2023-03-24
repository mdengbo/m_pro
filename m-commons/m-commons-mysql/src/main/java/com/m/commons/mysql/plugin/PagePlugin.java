package com.m.commons.mysql.plugin;

import com.m.commons.mysql.utils.MPageUtils;
import com.m.commons.mysql.utils.MybatisUtils;
import com.m.data.page.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @des 自定义mysql 分页插件
 * */
@Intercepts({
        @Signature(
                type = StatementHandler.class, //SimpleExecutor -> doQuery 至于查询之前处理，预编译时拦截处理
                method = "prepare", //
                args = {Connection.class, Integer.class} //
        )
})
@Slf4j
@Component
public class PagePlugin implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //获取拦截目标
        StatementHandler handler = (StatementHandler) MybatisUtils.getNoProxyObj(invocation.getTarget());
        //获取拦截sql
        BoundSql boundSql = handler.getBoundSql();
        String sql = boundSql.getSql().toLowerCase().replaceAll("\n", "");
        log.debug("[SQL PAGE_PLUGIN] 当前sql：{}", sql);

        //判断当前是否为 查询语句
        if(!sql.startsWith("select")) {
            //非查询放行
            return invocation.proceed();
        }

        //判断是否是分页查询 以便做分页处理
        Page page = MPageUtils.getPage();
        if(null == page) {
            log.debug("SQL PAGE_PLUGIN] 非分页查询");
            return invocation.proceed();
        }

        //分页处理
        log.debug("[SQL PAGE_PLUGIN] 开始分页处理");
        //计算总条数
        Integer pageCount = getCount(sql, invocation, handler);

        if(null == page.getPageSize()) {
            //设置默认值
            page.setPageSize(10);
        }
        //参数设置
        page.setPageCount(pageCount);
        page.setPageTotal(page.getPageCount() % page.getPageSize() == 0 ?
                        page.getPageCount() / page.getPageSize() :
                        page.getPageCount() / page.getPageSize() + 1
                );

        //当前页小于1 设为1
        if(page.getPageNum() < 1) {
            log.error("[SQL PAGE_PLUGIN] 分页处理，当前传入的分页数小于1，现以第一页返回，传入值pageNum:{}, 数据库总页数：{}", page.getPageNum(), page.getPageTotal());
            page.setPageNum(1);
        }
        //大于总分页数  设为总分页
        if(page.getPageNum() > page.getPageTotal() && page.getPageTotal() > 0) {
            log.error("[SQL PAGE_PLUGIN] 分页处理，当前传入的分页数大于总页数，现以最后一页返回，传入值pageNum:{}, 数据库总页数：{}", page.getPageNum(), page.getPageTotal());
            page.setPageNum(page.getPageTotal());
        }

        //执行分页 todo 不同数据库设计
        sql += " limit " + (page.getPageNum() -1 )* page.getPageSize() + " , " + page.getPageSize();
        log.debug("[SQL PAGE_PLUGIN] 分页sql拼接： {}", sql);

        //通过mybatis 工具将原有sql替换掉
        MetaObject metaObject = SystemMetaObject.forObject(boundSql);
        //metaObject 可设置私有属性值
        metaObject.setValue("sql", sql);

        log.debug("[SQL PAGE_PLUGIN] 分页完成");
        return invocation.proceed();
    }

    /**
     * 统计总条数
     * */
    private Integer getCount(String sql, Invocation invocation, StatementHandler handler) throws SQLException {

        //拼接计算总天数sql
        String countSql = "select count(*) as count " + sql.substring(MybatisUtils.getMasterFromIndex(sql, 0));
        log.debug("【SQL PAGE_PLUGIN】分页查询获取总条数的sql: {}", countSql);

        //获取数据库链接
        Connection connection = (Connection)invocation.getArgs()[0];
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            ps = connection.prepareStatement(countSql);
            //sql 参数占位符处理 select * from where age = ?
            handler.parameterize(ps);
            //sql 执行
            resultSet = ps.executeQuery();
            if(resultSet.next()) {
                int count = resultSet.getInt("count");
                log.debug("[SQL PAGE_PLUGIN] 分页查询总条数：{}", count);
                return count;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            log.error("[SQL PAGE_PLUGIN] 分页处理异常", e);
            throw e;
        } finally {
            if(resultSet != null) {
                resultSet.close();
            }

            if(ps != null) {
                ps.close();
            }
        }

        return 0;
    }
}
