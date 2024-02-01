package com.example.taskservice.mapper;



import com.example.taskservice.domain.Task;
import com.example.taskservice.domain.TaskDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO toTaskDTO(Task task);

    Task toTaskEntity(TaskDTO taskDTO);

}
