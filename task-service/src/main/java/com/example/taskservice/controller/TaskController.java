package com.example.taskservice.controller;

import com.example.taskservice.domain.TaskDTO;
import com.example.taskservice.service.TaskService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {


    @Autowired
    private TaskService taskService;


    @PostMapping("/create")
    public ResponseEntity<TaskDTO> createTask(@RequestBody @Valid TaskDTO projectDto) {
        return ResponseEntity.ok().body(taskService.createTask(projectDto));
    }

    @PutMapping("/update/{taskTitle}")
    public ResponseEntity<TaskDTO> updateTask(@RequestBody @Valid TaskDTO taskDto, @PathVariable("taskTitle") String taskTitle) {
        return ResponseEntity.ok().body(taskService.updateTask(taskDto, taskTitle));
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @GetMapping("/{userEmail}/{projectName}")
    public ResponseEntity<List<TaskDTO>> getAllTasksByUserProject(@PathVariable("userEmail") String userEmail, @PathVariable("projectName") String projectName) {
        return ResponseEntity.ok().body(taskService.getAllTasksByUserProject(userEmail, projectName));
    }


    @GetMapping("/unassigned/{projectName}")
    public ResponseEntity<List<TaskDTO>> getAllTasksUnassignedProject(@PathVariable("projectName") String projectName) {
        return ResponseEntity.ok().body(taskService.getAllTasksUnassignedProject(projectName));
    }


}
