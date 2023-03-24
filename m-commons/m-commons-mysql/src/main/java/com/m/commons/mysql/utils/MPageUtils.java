package com.m.commons.mysql.utils;

import com.m.data.page.Page;
import lombok.Data;
/**
* 工具类
* */
@Data
public class MPageUtils {

    /**
     * 线程私有
     * */
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();

    /**
     * 设置分页信息
     * */
    public static void setPage(Integer pageNum, Integer pageSize) {
        Page page = new Page();
        page.setPageNum(pageNum);
        page.setPageSize(pageSize);
        pageThreadLocal.set(page);
    }

    /**
     * 获取page
     * */
    public static Page getPage() {
        return pageThreadLocal.get();
    }

    /**
     * 清除
     * */
    public static void clear() {
        pageThreadLocal.remove();
    }
}
