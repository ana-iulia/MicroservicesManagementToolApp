package com.example.projectservice.controller;

import com.example.projectservice.dto.ProjectDTO;
import com.example.projectservice.service.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class ProjectController {


    @Autowired
    private ProjectService projectService;


    @PostMapping("/create/project")
    public ResponseEntity<ProjectDTO> createProject(@RequestBody @Valid ProjectDTO projectDto) {
        return ResponseEntity.ok().body(projectService.saveProject(projectDto));
    }

    @CrossOrigin
    @PutMapping("/assign/project/{projectName}/{userEmail}")
    public ResponseEntity<String> assignProject(@PathVariable("projectName") String projectName, @PathVariable("userEmail") String userEmail) {

        projectService.assignProject(projectName, userEmail);
        return new ResponseEntity<>("Project details sent to RabbitMQ ...", HttpStatus.OK);
    }


    @PutMapping("/update/project/{oldProjectName}/{newProjectName}")
    public ResponseEntity<String> updateProjectName(@PathVariable("oldProjectName") String oldProjectName, @PathVariable("newProjectName") String newProjectName) {

        projectService.updateProjectName(oldProjectName, newProjectName);
        return new ResponseEntity<>("Project details sent to RabbitMQ ...", HttpStatus.OK);
    }


    @GetMapping("/projects")
    public ResponseEntity<List<ProjectDTO>> getAll() {
        return new ResponseEntity<>(projectService.getAllProjects(), HttpStatus.OK);
    }


}
