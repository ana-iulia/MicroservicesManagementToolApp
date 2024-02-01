package com.example.userservice.repo;

import com.example.userservice.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findAllByUserId(Integer userId);
}
