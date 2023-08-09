package com.example.kafka.controller;

import com.common.commonsuite.dto.KafkaDto;
import com.example.kafka.producer.KafkaProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaProducer producer;

    public KafkaController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/message")
    public String sendMessage(@RequestParam("message") String message) {
        KafkaDto kafkaDto = KafkaDto.builder()
                .yyyymmdd("2021-01-01")
                .skuCd("10300000033")
                .fieldName("ipgoNo")
                .diff(100).build();
        this.producer.sendMessage(kafkaDto);
        return "success";
    }

}
