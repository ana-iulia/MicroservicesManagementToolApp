package com.example.projectservice.mapper;


import com.example.projectservice.domain.Project;
import com.example.projectservice.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ProjectMapper {

    ProjectDTO toProjectDTO(Project project);


    Project toProjectEntity(ProjectDTO projectDTO);


}
