package com.m.commons.config;

import com.m.commons.property.MyProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.m.business"})
@EnableConfigurationProperties(value = {MyProperties.class})
public class BaseConfiguration {

}
