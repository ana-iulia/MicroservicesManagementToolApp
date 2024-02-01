package com.example.taskservice.service;

import com.example.common.Task.NotificationDTO;
import com.example.taskservice.domain.Task;
import com.example.taskservice.domain.TaskDTO;
import com.example.taskservice.kafka.JsonKafkaProducer;
import com.example.taskservice.mapper.TaskMapper;
import com.example.taskservice.repo.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    private static final String ERROR_NOTIGFICATION = "You do not have permission for this request.";
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskMapper taskMapper;

    @Autowired
    private JsonKafkaProducer kafkaProducer;


    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task task = taskRepository.save(taskMapper.toTaskEntity(taskDTO));
        if (task.getUserEmail() != null) {
            String message = String.format("You were assigned to task %s in project %s", task.getTitle(), task.getProjectName());
            NotificationDTO notificationDTO = new NotificationDTO(task.getId(), message, task.getUserEmail(), task.getProjectName());
            kafkaProducer.sendMessage(notificationDTO);
        }
        return taskMapper.toTaskDTO(task);
    }

    @Override
    public TaskDTO updateTask(TaskDTO taskDTO, String taskTitle) {
        Task taskOptional = taskRepository.findByTitle(taskTitle);
        Task task = taskMapper.toTaskEntity(taskDTO);
        task.setTitle(taskTitle);
        task.setProjectName(taskOptional.getProjectName());
        task.setId(taskOptional.getId());

        if (taskOptional != null) {
            if (taskOptional.getUserEmail() == null && task.getUserEmail() != null) {
                //mesaj new assign
                String message = String.format("You were assigned to task %s in project %s.", taskOptional.getTitle(), taskOptional.getProjectName());
                NotificationDTO notificationDTO = new NotificationDTO(task.getId(), message, task.getUserEmail(), task.getProjectName());
                kafkaProducer.sendMessage(notificationDTO);
            } else if (taskOptional.getUserEmail() != null && !taskOptional.getTaskStatus().equals(task.getTaskStatus())) {
                String message = String.format("Task %s was changed from %s to %s in project %s.", taskOptional.getTitle(), taskOptional.getTaskStatus(), task.getTaskStatus(), taskOptional.getProjectName());
                NotificationDTO notificationDTO = new NotificationDTO(task.getId(), message, task.getUserEmail(), task.getProjectName());
                kafkaProducer.sendMessage(notificationDTO);
            } else if (taskOptional.getUserEmail() != null && !taskOptional.getDescription().equals(task.getDescription())) {
                String message = String.format("Task %s in project %s: description was changed.", taskOptional.getTitle(), taskOptional.getProjectName());
                NotificationDTO notificationDTO = new NotificationDTO(task.getId(), message, task.getUserEmail(), task.getProjectName());
                kafkaProducer.sendMessage(notificationDTO);
            }

        }
        task = taskRepository.save(task);
        return taskMapper.toTaskDTO(task);
    }


    @Override
    public List<TaskDTO> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::toTaskDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> getAllTasksByUserProject(String userEmail, String projectName) {
        return taskRepository.findAllByUserEmailAndAndProjectName(userEmail, projectName)
                .stream()
                .map(taskMapper::toTaskDTO)
                .toList();
    }

    @Override
    public List<TaskDTO> getAllTasksUnassignedProject(String projectName) {
        return taskRepository.findAllByProjectName(projectName)
                .stream()
                .filter(task -> task.getUserEmail() == null)
                .map(taskMapper::toTaskDTO)
                .toList();
    }


}
