package com.example.projectservice.service.impl;

import com.example.projectservice.domain.Project;
import com.example.projectservice.domain.ProjectDetailEvent;
import com.example.projectservice.dto.APIResponseDto;
import com.example.projectservice.dto.ProjectDTO;
import com.example.projectservice.mapper.ProjectMapper;
import com.example.projectservice.publisher.ProjectProducer;
import com.example.projectservice.repository.ProjectRepository;
import com.example.projectservice.service.ProjectService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.NewParentTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private static final String UPDATE_PROJECT_EVENT = "update_project";
    private static final String ASSIGN_PROJECT_EVENT = "assign_project";

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectProducer projectProducer;

    @Override
    @Transactional
    public ProjectDTO saveProject(ProjectDTO projectDto) {
        Project project = projectMapper.toProjectEntity(projectDto);
        return projectMapper.toProjectDTO(projectRepository.save(project));
    }

    @Override
    public APIResponseDto getProjectById(Long projectId) {
        return null;
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream().map(projectMapper::toProjectDTO).toList();

    }

    @Override
    public void assignProject(String projectName, String userEmail) {
        ProjectDetailEvent event = new ProjectDetailEvent();
        event.setFirstMessage(projectName);
        event.setSecondMessage(userEmail);
        event.setEventType(ASSIGN_PROJECT_EVENT);
        projectProducer.sendMessage(event);
    }

    @Override
    @Transactional
    public void updateProjectName(String oldProjectName, String newProjectName) {
        ProjectDetailEvent event = new ProjectDetailEvent();
        Project project = projectRepository.findByName(oldProjectName);
        project.setName(newProjectName);
        event.setFirstMessage(oldProjectName);
        event.setSecondMessage(newProjectName);
        event.setEventType(UPDATE_PROJECT_EVENT);
        projectProducer.sendMessage(event);
    }

}
