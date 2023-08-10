package com.example.kafka.consumer;

import com.common.commonsuite.dto.KafkaDto;
import com.example.kafka.dto.AuthorizerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "#{paymentEvent.name}", groupId = "suite", containerFactory = "stockChangeListener")
    public void consume(KafkaDto message) throws IOException {
        AuthorizerDto authorizerDto = objectMapper.convertValue(message.getData(), AuthorizerDto.class);
        log.info("Consumed Message Name : {}", authorizerDto.getName());
        log.info("Consumed Message MemberId : {}", authorizerDto.getMemberId());


    }
}
