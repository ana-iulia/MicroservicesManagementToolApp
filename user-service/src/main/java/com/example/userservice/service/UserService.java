package com.example.userservice.service;

import com.example.common.Task.NotificationDTO;
import com.example.userservice.domain.*;
import com.example.userservice.mapper.NotificationMapper;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.repo.NotificationRepository;
import com.example.userservice.repo.UserProjectAssignmentRepository;
import com.example.userservice.repo.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    private static final String ERROR_MESSAGE = "You do not have permission for this request.";
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserProjectAssignmentRepository userProjectAssignmentRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private NotificationMapper notificationMapper;


    @Override
    public Integer login(LoginRequestDTO loginRequestDTO) throws Exception {

        User user = userRepository.findByEmail(loginRequestDTO.getEmail());

        if (user != null) {
            return user.getId();
        }

        throw new Exception("Bad credentials");
    }

    @Override
    public void registerUser(User user) throws Exception {
        try {
            userRepository.save(user);
        } catch (Exception ex) {
            throw new Exception("Duplicate email entry");
        }


    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();

    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toUserDTO)
                .toList();
    }

    @Override
    public List<String> getAllAssignments(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user != null) {
            return userProjectAssignmentRepository.findAllByUserId(user.getId())
                    .stream()
                    .map(UserProjectAssignment::getProjectName)
                    .toList();
        }
        return Collections.emptyList();
    }

    @SneakyThrows
    @Override
    public UserDTO getUser(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new Exception("User doesn't exist");
        }
        return userMapper.toUserDTO(user.get());

    }

    @Override
    @Transactional
    public UserDTO saveRegularUser(UserRegisterDTO userDTO) {
        User user = userMapper.toUserEntity(userDTO);
        return userMapper.toUserDTO(userRepository.save(user));

    }

    @Override
    @Transactional
    public void assignUserToProject(ProjectDetailEvent projectDetailEvent) {
        UserProjectAssignment userProjectAssignment = new UserProjectAssignment();
        userProjectAssignment.setProjectName(projectDetailEvent.getFirstMessage());

        User user = userRepository.findByEmail(projectDetailEvent.getSecondMessage());
        userProjectAssignment.setUser(user);
        if (user != null) {
            user.getProjectAssignmets().add(userProjectAssignment);
            userProjectAssignmentRepository.save(userProjectAssignment);
        }

    }

    @Override
    @Transactional
    public void updateProjectName(ProjectDetailEvent projectDetailEvent) {
        List<UserProjectAssignment> userProjectAssignmentList = userProjectAssignmentRepository.findAllByProjectName(projectDetailEvent.getFirstMessage());
        for (UserProjectAssignment assignment : userProjectAssignmentList) {
            assignment.setProjectName(projectDetailEvent.getSecondMessage());
        }
    }

    @Override
    @Transactional
    public void saveNotificationUser(NotificationDTO notificationDTO) {
        User user = userRepository.findByEmail(notificationDTO.getUserEmail());
        if (user != null) {
            Notification notification = new Notification(null, user, notificationDTO.getDescription(), false);
            notification = notificationRepository.save(notification);
            user.getNotifications().add(notification);
        }
    }

    @Override
    public List<NotificationEventDTO> getUserNotifications(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        return notificationRepository.findAllByUserId(user.getId()).stream().filter(notification -> !notification.getIsRead()).map(notificationMapper::toNotificationDTO).toList();

    }

    @Override
    @Transactional
    public Boolean readUserNotifications(String userEmail) {
        User user = userRepository.findByEmail(userEmail);

        List<Notification> notifications = notificationRepository.findAllByUserId(user.getId()).stream().filter(notification -> !notification.getIsRead()).toList();
        for (Notification notification : notifications) {
            notification.setIsRead(true);
        }
        return true;

    }

}
