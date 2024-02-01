package com.example.userservice.service;

import com.example.common.Task.NotificationDTO;
import com.example.userservice.domain.*;

import java.util.List;

public interface IUserService {

    List<UserDTO> getAllUsers();

    List<String> getAllAssignments(String userEmail);

    Integer login(LoginRequestDTO loginRequestDTO) throws Exception;

    void registerUser(User user) throws Exception;

    List<User> findAll();

    UserDTO saveRegularUser(UserRegisterDTO userDTO);

    UserDTO getUser(Integer id) throws Exception;

    void assignUserToProject(ProjectDetailEvent projectDetailEvent);

    void updateProjectName(ProjectDetailEvent projectDetailEvent);

    void saveNotificationUser(NotificationDTO notificationDTO);

    List<NotificationEventDTO> getUserNotifications(String userEmail);
    Boolean readUserNotifications(String userEmail);
}