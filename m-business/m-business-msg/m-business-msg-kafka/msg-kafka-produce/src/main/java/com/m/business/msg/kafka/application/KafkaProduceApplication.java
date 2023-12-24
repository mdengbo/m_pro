package com.m.business.msg.kafka.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * kafka 生产者
 * */
@SpringBootApplication
public class KafkaProduceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProduceApplication.class, args);
    }
}
