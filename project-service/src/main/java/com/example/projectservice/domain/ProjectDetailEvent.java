package com.example.projectservice.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectDetailEvent {

    private String firstMessage;
    private String secondMessage;
    private String eventType;

}
