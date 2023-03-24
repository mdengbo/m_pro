package com.m.business.student.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan("com.m.data.mapper") //dao扫描路径 可统一抽取到 m-commons-mysql 全局处理
public class StuApplication {
    public static void main(String[] args) {
        SpringApplication.run(StuApplication.class, args);
    }
}
