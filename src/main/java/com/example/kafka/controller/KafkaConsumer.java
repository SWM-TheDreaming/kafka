package com.example.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "#{myTopic1.name}", groupId = "group1")
    public void consume(String message) throws IOException {
      log.info("Consumed Message : {}", message);
    }
}
