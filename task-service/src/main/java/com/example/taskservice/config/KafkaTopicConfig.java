package com.example.taskservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String kafkaTopic;

    @Value("${spring.kafka.json.topic.name}")
    private String kafkaTopicJson;

    @Bean
    public NewTopic kafkaTopic() {
        return TopicBuilder.name(kafkaTopic)
                .build();
    }

    @Bean
    public NewTopic kafkaTopicJson() {
        return TopicBuilder.name(kafkaTopicJson)
                .build();
    }
}
