package com.example.common.Task;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NotificationDTO {
    private Integer taskId;

    private String description;

    private String userEmail;

    private String projectName;
}
