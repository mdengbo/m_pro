package com.m.business.msg.kafka.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.m.business.msg.kafka.vo.KafkaMsgBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaUtils {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    //发送消息到kafka
    public boolean sendMsg(String topic, String json) {
        if (StrUtil.isBlank(topic) || StrUtil.isBlank(json)) {
            return false;
        }
        kafkaTemplate.send(topic, json);
        return true;
    }

    //发送消息到kafka
    public boolean sendMsg(String topic, String key, String json) {
        if (StrUtil.isBlank(topic) || StrUtil.isBlank(json)) {
            return false;
        }
        kafkaTemplate.send(topic, key, json);
        return true;
    }

    //批量存储
    public boolean sendBatchMsg(String topic, List<KafkaMsgBean> msgs) {
        if (CollUtil.isEmpty(msgs)) {
            return false;
        }
        msgs.forEach(msg -> {
            sendMsg(topic, msg.getDeviceId(), msg.getMsgContent());
        });
        return true;
    }
}

