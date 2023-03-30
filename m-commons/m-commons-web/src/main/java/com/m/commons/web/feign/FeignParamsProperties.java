package com.m.commons.web.feign;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * @Description 自定义 feign 拦截器所拦截的参数信息
 * @Author Mdengbo
 * @Date 2023/3/30 下午3:35
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "feign")
@Data
public class FeignParamsProperties {

    /**
     * 需要传递的参数列表
     * */
    private List<String> paramsName;
}
