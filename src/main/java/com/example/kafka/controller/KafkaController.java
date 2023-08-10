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


        return "success";
    }

}
