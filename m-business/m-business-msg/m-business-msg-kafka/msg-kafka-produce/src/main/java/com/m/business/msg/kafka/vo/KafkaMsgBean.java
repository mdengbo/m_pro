package com.m.business.msg.kafka.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KafkaMsgBean {

    private String deviceId;
    private String msgContent;
}
