package com.example.userservice.kafka;
import com.example.common.Task.NotificationDTO;
import com.example.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaConsumer.class);
    @Autowired
    private UserService userService;

    @KafkaListener(topics = "${spring.kafka.json.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(NotificationDTO task) {
        LOGGER.info(String.format("Json message received -> %s", task.toString()));
        userService.saveNotificationUser(task);

    }
}

