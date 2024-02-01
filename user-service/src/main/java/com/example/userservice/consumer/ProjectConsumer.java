package com.example.userservice.consumer;

import com.example.userservice.domain.ProjectDetailEvent;
import com.example.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectConsumer {

    @Autowired
    private UserService userService;

    private Logger LOGGER = LoggerFactory.getLogger(ProjectConsumer.class);
    private static final String UPDATE_PROJECT_EVENT = "update_project";
    private static final String ASSIGN_PROJECT_EVENT = "assign_project";

    @RabbitListener(queues = "${rabbitmq.queue.user.name}")
    public void consume(ProjectDetailEvent projectDetailEvent) {
        LOGGER.info(String.format("Project event received => %s", projectDetailEvent.toString()));
        switch (projectDetailEvent.getEventType()) {
            case ASSIGN_PROJECT_EVENT:
                userService.assignUserToProject(projectDetailEvent);
                break;
            default:
                userService.updateProjectName(projectDetailEvent);

                //save project event data in database
        }
    }
}
