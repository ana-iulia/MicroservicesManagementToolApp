package com.example.projectservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${rabbitmq.queue.order.name}")
    private String taskQueue;

    @Value("${rabbitmq.queue.user.name}")
    private String userQueue;

    @Value("${rabbitmq.exchange.name}")
    private String taskExchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String taskRoutingKey;

    @Value("${rabbitmq.binding.user.routing.key}")
    private String userRoutingKey;

    //spring bean for queue - task queue
    @Bean
    public Queue taskQueue() {
        return new Queue(taskQueue);
    }
    //spring bean for queue - user queue
    @Bean
    public Queue userQueue() {
        return new Queue(userQueue);
    }

    //spring bean for exchange
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(taskExchange);
    }

    //spring bean for binding between exchange and queue using outing key
    @Bean
    public Binding taskBinding() {
        return BindingBuilder
                .bind(taskQueue())
                .to(exchange())
                .with(taskRoutingKey);
    }

    //spring bean for binding between exchange and queue using outing key
    @Bean
    public Binding userBinding() {
        return BindingBuilder
                .bind(userQueue())
                .to(exchange())
                .with(userRoutingKey);
    }

    //message converter
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    //configure RabbitTemplate
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
