package com.example.projectservice.service;


import com.example.projectservice.domain.Project;
import com.example.projectservice.dto.APIResponseDto;
import com.example.projectservice.dto.ProjectDTO;

import java.util.List;

public interface ProjectService {
    ProjectDTO saveProject(ProjectDTO employeeDto);

    List<ProjectDTO> getAllProjects();

    APIResponseDto getProjectById(Long projectId);

    void assignProject(String projectName, String userEmail);

    void updateProjectName(String oldProjectName, String newProjectName);
}
