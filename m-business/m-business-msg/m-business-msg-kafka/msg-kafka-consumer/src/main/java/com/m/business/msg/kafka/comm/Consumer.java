package com.m.business.msg.kafka.comm;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;

@Slf4j
public abstract class Consumer {

    public void listenTopic(ConsumerRecord<String, String> record) {
        String topic = record.topic();
        String value = record.value();
        log.info("kafka的key:{},value:{} ", topic, value);
        if (JSONUtil.isTypeJSON(value)) {
            consumerTopic(topic, value);
        }
    }

    public void add(String value) {
        this.consumerTopic(null, value);
    }

    //执行消费逻辑
    public abstract void consumerTopic(String topic, String value);
}

