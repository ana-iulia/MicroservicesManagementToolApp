package com.example.taskservice.service;

import com.example.taskservice.domain.TaskDTO;

import java.util.List;

public interface ITaskService {

    TaskDTO createTask(TaskDTO taskDTO);
    List<TaskDTO> getAllTasks();
    List<TaskDTO> getAllTasksByUserProject(String userEmail, String projectName);
    TaskDTO updateTask(TaskDTO taskDTO, String taskTitle);
    List<TaskDTO> getAllTasksUnassignedProject(String projectName);
}