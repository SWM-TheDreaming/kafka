package com.example.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

    private final NewTopic myTopic1;
    private final NewTopic myTopic2;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate kafkaTemplate, @Qualifier("myTopic1") NewTopic myTopic1, @Qualifier("myTopic2") NewTopic myTopic2) {
        this.myTopic1 = myTopic1;
        this.myTopic2 = myTopic2;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String message) {
        log.info("Produce message : {}", message);
        this.kafkaTemplate.send(myTopic1.name(), message);
    }

}
