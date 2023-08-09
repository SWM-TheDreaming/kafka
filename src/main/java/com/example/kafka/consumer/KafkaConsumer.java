package com.example.kafka.consumer;

import com.common.commonsuite.dto.KafkaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "#{paymentEvent.name}", groupId = "suite", containerFactory = "stockChangeListener")
    public void consume(KafkaDto message) throws IOException {
        log.info("Consumed Message yyyymmdd : {}", message.getYyyymmdd());
        log.info("Consumed Message diff : {}", message.getDiff());
        log.info("Consumed Message fieldName : {}", message.getFieldName());
        log.info("Consumed Message skuCd : {}", message.getSkuCd());
    }
}
