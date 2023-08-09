package com.example.kafka.config;


import com.common.commonsuite.dto.KafkaDto;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
    // 테스트 Topic 생성 1
    @Bean
    public NewTopic paymentEvent() {
        return new NewTopic("payment_event", 1, (short) 1);
    }

    // 테스트 Topic 생성 2
    @Bean
    public NewTopic myTopic2() {
        return new NewTopic("my_topic_2", 1, (short) 1);
    }

    @Bean
    public ProducerFactory<String, KafkaDto> producerFactory() {
        Map<String,Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<String, KafkaDto>(configs);
    }

    @Bean
    public KafkaTemplate<String, KafkaDto> kafkaTemplate() {
        return new KafkaTemplate<String, KafkaDto>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, KafkaDto> stockChangeConsumer() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "suite");

        // JSON Deserialization Configuration
        JsonDeserializer<KafkaDto> jsonDeserializer = new JsonDeserializer<>(KafkaDto.class);
        jsonDeserializer.addTrustedPackages("*"); // Allow deserialization of all packages

        ErrorHandlingDeserializer<KafkaDto> errorHandlingDeserializer = new ErrorHandlingDeserializer<>(jsonDeserializer);

        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configs.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class); // If you want error handling for keys as well
        configs.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(
                configs,
                new StringDeserializer(),
                errorHandlingDeserializer);  // Use the errorHandlingDeserializer here
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, KafkaDto> stockChangeListener() {
        ConcurrentKafkaListenerContainerFactory<String, KafkaDto> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(stockChangeConsumer());
        return factory;
    }




}
