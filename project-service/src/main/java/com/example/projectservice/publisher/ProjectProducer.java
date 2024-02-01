package com.example.projectservice.publisher;

import com.example.projectservice.domain.ProjectDetailEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ProjectProducer {

    private Logger LOGGER = LoggerFactory.getLogger(ProjectProducer.class);

    @Value("${rabbitmq.exchange.name}")
    private String taskExchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String taskRoutingKey;

    @Value("${rabbitmq.binding.user.routing.key}")
    private String userRoutingKey;


    private RabbitTemplate rabbitTemplate;

    public ProjectProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ProjectDetailEvent projectDetailEvent) {
        LOGGER.info(String.format("Project event sent to RabbitMQ task=> %s", projectDetailEvent.toString()));
        rabbitTemplate.convertAndSend(taskExchange, taskRoutingKey, projectDetailEvent);


        LOGGER.info(String.format("Project event sent to RabbitMQ user=> %s", projectDetailEvent.toString()));
        rabbitTemplate.convertAndSend(taskExchange, userRoutingKey, projectDetailEvent);


    }
}
