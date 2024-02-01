package com.example.taskservice.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskDTO {

    private String title;

    private String description;

    private TaskStatus taskStatus;

    private String userEmail;

    private String projectName;
}
