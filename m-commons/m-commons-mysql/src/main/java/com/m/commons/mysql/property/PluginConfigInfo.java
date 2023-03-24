package com.m.commons.mysql.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
/**
 * 实现 yml自定义提示
 * 当在 yml 文件写入 m.plugin.sql/page.enable 时会有对应属性提示
 * */

@Data
@ConfigurationProperties(prefix = "m.plugin")
public class PluginConfigInfo {

    private Sql sql;
    private Page page;

    @Data
    static class Sql{
        private boolean enable;
    }

    @Data
    static class Page{
        private boolean enable = true;
    }
}
