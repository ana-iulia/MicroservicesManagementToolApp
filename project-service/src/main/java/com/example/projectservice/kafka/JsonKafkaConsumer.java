package com.example.projectservice.kafka;

import com.example.common.Task.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);

    @KafkaListener(topics = "${spring.kafka.json.topic.name}", groupId = "project")
    public void consumer(NotificationDTO task) {
        LOGGER.info(String.format("Json message received -> %s", task.toString()));

    }
}

