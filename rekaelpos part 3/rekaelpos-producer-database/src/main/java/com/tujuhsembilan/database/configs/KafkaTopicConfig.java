package com.tujuhsembilan.database.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic customerTopic(){

        return TopicBuilder.name("topic-customer")
                .partitions(4).replicas(1)
                .build();
    }
}
