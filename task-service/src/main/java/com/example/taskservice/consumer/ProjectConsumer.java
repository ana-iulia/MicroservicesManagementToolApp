package com.example.taskservice.consumer;

import com.example.taskservice.domain.ProjectDetailEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ProjectConsumer {
    private Logger LOGGER= LoggerFactory.getLogger(ProjectConsumer.class);

    @RabbitListener(queues = "${rabbitmq.queue.order.name}")
    public void consume(ProjectDetailEvent projectDetailEvent){
        LOGGER.info(String.format("Project event received => %s",projectDetailEvent.toString()));

        //save project event data in database
    }
}
