package com.example.kafka.producer;


import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaProducer {

 /*   private final NewTopic paymentEvent;
    private final KafkaTemplate<String, KafkaDto> kafkaTemplate;

    @Autowired
    public KafkaProducer(KafkaTemplate<String, KafkaDto> kafkaTemplate, @Qualifier("paymentEvent") NewTopic paymentEvent) {
        this.paymentEvent = paymentEvent;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaDto message) {
        log.info("Produce message : {}", message);
        this.kafkaTemplate.send(paymentEvent.name(), message);
    }
*/
}
