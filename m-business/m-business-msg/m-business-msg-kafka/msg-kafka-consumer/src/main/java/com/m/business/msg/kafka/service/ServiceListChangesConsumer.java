package com.m.business.msg.kafka.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.m.business.msg.kafka.comm.Consumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ServiceListChangesConsumer extends Consumer {

    public static final String Upstream_C2S_Topic = "Upstream_C2S_Topic";

    @Override
    @KafkaListener(topicPattern = Upstream_C2S_Topic)
    public void listenTopic(ConsumerRecord<String, String> record) {
        super.listenTopic(record);
    }

    // 执行消费逻辑
    @Override
    public void consumerTopic(String topic, String value) {
        JSONObject jsonObject = JSONUtil.parseObj(value);
        log.info("msg:{}", value);
        String vin = jsonObject.getStr("vin");
        System.out.println("consumer:" + vin + ",eventID:" + jsonObject.getStr("eventID"));
        log.info("VIN={},ServiceListChangesConsumer消费成功，消息id={} ", vin, jsonObject.getStr("eventID"));
    }

}
