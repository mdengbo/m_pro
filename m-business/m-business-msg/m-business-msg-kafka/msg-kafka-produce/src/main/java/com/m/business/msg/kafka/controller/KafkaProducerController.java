package com.m.business.msg.kafka.controller;

import com.alibaba.fastjson.JSONObject;
import com.m.data.r.R;
import com.m.data.r.RUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka/v1")
@Slf4j
public class KafkaProducerController {

    public static final String Upstream_C2S_Topic = "Upstream_C2S_Topic";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @ResponseBody
    @PostMapping(value = "/serviceListChanges", produces = "application/json")
    public R serviceListChanges(@RequestBody JSONObject jsonData) {
        log.info("URL = {},vin={}, 请求的jsonObject的值 = {}",
                "serviceListChanges", jsonData.getString("vin"), jsonData);
        try {
            kafkaTemplate.send(Upstream_C2S_Topic, jsonData.getString("key"),jsonData.toString());
            jsonData.put("success", true);
            return RUtils.succ(jsonData);
        } catch (Exception e) {
            log.error("KafkaProducerController serviceListChanges error = {}", e.getMessage());
        }
        return RUtils.fail();
    }

    @RequestMapping(value = "/get")
    public R get(String name) {

        return RUtils.fail(name);
    }
}

