package com.m.business.classes.application;

import com.m.commons.web.apiversion.register.EnableApiVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Description 班级
 * @Author Mdengbo
 * @Date 2023/3/29 上午10:04
 * @Version 1.0
 */
@SpringBootApplication
@EnableApiVersion
public class ClassesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassesApplication.class, args);
    }
}
