package com.example.taskservice.repo;

import com.example.taskservice.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
   List<Task> findAllByUserEmailAndAndProjectName(String userEmail, String projectName);
   List<Task> findAllByProjectName(String projectName);
   Task findByTitle(String title);




}
