package com.example.kafka.config;


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

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean public NewTopic SuiteRoomJoin() {
        return new NewTopic("SuiteRoom-Join", 3, (short) 1);
    }
    @Bean public NewTopic SuiteRoomJoinError() {
        return new NewTopic("SuiteRoom-Join-Error", 3, (short) 1);
    }
    @Bean public NewTopic JoinCompletionNotification() {return new NewTopic("Join-Completion-Notification", 3, (short) 1);}
    @Bean public NewTopic SuiteRoomTerminate() {
        return new NewTopic("SuiteRoom-Terminate", 3, (short) 1);
    }
    @Bean public NewTopic SuiteRoomTerminateError() {
        return new NewTopic("SuiteRoom-Terminate-Error", 3, (short) 1);
    }
    @Bean public NewTopic TerminateNotification() {
        return new NewTopic("Terminate-Notification", 3, (short) 1);
    }
    @Bean public NewTopic UserRegistrationFCM() {
        return new NewTopic("User-Registration-FCM", 3, (short) 1);
    }
    @Bean public NewTopic UserRegistrationUserMetaInfo() {return new NewTopic("User-Registration-UserMetaInfo", 3, (short) 1);}
    @Bean public NewTopic SuiteRoomCancelJoin() {
        return new NewTopic("SuiteRoom-CancelJoin", 3, (short) 1);
    }
    @Bean public NewTopic SuiteRoomCancelJoinError() {return new NewTopic("SuiteRoom-CancelJoin-Error", 3, (short) 1);}
    @Bean public NewTopic JoinCancelJoinNotification() {return new NewTopic("Join-CancelJoin-Notification", 3, (short) 1);}
    @Bean public NewTopic SuiteRoomContractCreation() {return new NewTopic("SuiteRoom-Contract-Creation", 3, (short) 1);}
    @Bean public NewTopic ContractDeliveryNotification() {return new NewTopic("Contract-Delivery-Notification", 3, (short) 1);}
    @Bean public NewTopic StudyMissionCreation() {
        return new NewTopic("Study-MissionCreation", 3, (short) 1);
    }
    @Bean public NewTopic StudyMissionCreationError() {return new NewTopic("Study-MissionCreation-Error", 3, (short) 1);}
    @Bean public NewTopic HallOfFameNotification() {return new NewTopic("HallOfFame-Notification", 3, (short) 1);}
    @Bean public NewTopic HallOfFameNotificationError() {return new NewTopic("HallOfFame-Notification-Error", 3, (short) 1);}
    @Bean public NewTopic SuiteRoomStart() {return new NewTopic("SuiteRoom-Start", 3, (short) 1);}
    @Bean public NewTopic SuiteRoomStartError() {return new NewTopic("SuiteRoom-Start-Error", 3, (short) 1);}
    @Bean public NewTopic StartNotification() {return new NewTopic("Start-Notification", 3, (short) 1);}
    @Bean public NewTopic StudyStop() {return new NewTopic("Study-Stop", 3, (short) 1);}
    @Bean public NewTopic DepositDetail() {return new NewTopic("Deposit-Detail", 3, (short) 1);}
    @Bean public NewTopic SuiteRoomEnd() {
        return new NewTopic("SuiteRoom-End", 3, (short) 1);
    }

    @Bean public NewTopic StoppedStudy() {
        return new NewTopic("Stopped-Study", 3, (short) 1);
    }
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String,Object> configs = new HashMap<>();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "suite");
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), new StringDeserializer());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
