package com.m.commons.mysql.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * 自定义mybatis 插件
 * */
@Slf4j
public class MybatisUtils {

    /**
     * sql from 关键字
     * */
    private static String FROM_KEYWORD = " from ";

    /**
     * 获取非代理对象
     * */
    public static Object getNoProxyObj(Object tagert) {

        MetaObject metaObject = SystemMetaObject.forObject(tagert);
        //目标对象含有 h 属性证明时代理对象
        // h.target 为代理的真是对象
        while (metaObject.hasGetter("h")) {
            tagert = metaObject.getValue("h.target");
            metaObject = SystemMetaObject.forObject(tagert);
        }

        return tagert;
    }

    /**
     * 查找 from 关键字下标
     * 避免： select a, (select b, c, (select d from d), from b) from a where b in (select c from c); 计算猪表 from 位置出错
     * */
    public static Integer getMasterFromIndex(String sql, Integer beginIndex) {

        sql = sql.toLowerCase(Locale.ROOT);
        if(StringUtils.isEmpty(sql)) {
            log.error("[SQL FROM_INDEX] sql为空");
            return -1;
        }

        int fromIndex = sql.indexOf(FROM_KEYWORD, beginIndex);
        if (fromIndex == -1) {
            log.error("[SQL FROM_INDEX] sql中未包含关键字 from");
            return -1;
        }

        //通过判断当前下标前的左右括号数是否一致以确定是否是主表的from位置
        String sqlSub = sql.substring(0, fromIndex);
        int count = 0;
        char[] chars = sqlSub.toCharArray();
        for (char aChar : chars) {
            if(aChar == '(') count ++;
            if(aChar == ')') count --;
        }

        if (count == 0) {
            return fromIndex;
        }

        //递归查询
        return getMasterFromIndex(sql, fromIndex + 1);
    }

    public static void main(String[] args) {

        String sql = "select a, (select b, c, (select d from d), from b) from a where b in (select c from c)";
        String countSql = "select count(*) as count " + sql.substring(getMasterFromIndex(sql, 0));
        System.out.println(countSql);

    }

}
