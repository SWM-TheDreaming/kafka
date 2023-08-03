package com.example.kafka.controller;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
@EnableKafka
@Configuration
public class KafkaConfig {

    // 테스트 Topic 생성 1
    @Bean
    public NewTopic myTopic1() {
        return new NewTopic("my_topic_1", 1, (short) 1);
    }

    // 테스트 Topic 생성 2
    @Bean
    public NewTopic myTopic2() {
        return new NewTopic("my_topic_2", 1, (short) 1);
    }



}
