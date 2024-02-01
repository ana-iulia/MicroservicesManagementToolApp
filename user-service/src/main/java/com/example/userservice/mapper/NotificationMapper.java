package com.example.userservice.mapper;

import com.example.userservice.domain.Notification;
import com.example.userservice.domain.NotificationEventDTO;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface NotificationMapper {

    NotificationEventDTO toNotificationDTO(Notification notification);

    Notification toNotificationEntity(NotificationEventDTO notificationDTO);
}
