package com.example.taskservice.kafka;

import com.example.common.Task.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {


    @Value("${spring.kafka.json.topic.name}")
    private String kafkaTopicJson;

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);

    private KafkaTemplate<String, NotificationDTO> kafkaTemplate;

    public JsonKafkaProducer(KafkaTemplate<String, NotificationDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(NotificationDTO data) {

        LOGGER.info(String.format("Meassage sent -> %s", data.toString()));
        Message<NotificationDTO> message = MessageBuilder
                .withPayload(data)
                .setHeader(KafkaHeaders.TOPIC, kafkaTopicJson)
                .build();

        kafkaTemplate.send(message);
    }
}
