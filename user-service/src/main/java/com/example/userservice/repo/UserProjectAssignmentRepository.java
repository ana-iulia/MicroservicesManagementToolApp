package com.example.userservice.repo;

import com.example.userservice.domain.UserProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProjectAssignmentRepository extends JpaRepository<UserProjectAssignment, Integer> {

    List<UserProjectAssignment> findAllByProjectName(String projectName);
    List<UserProjectAssignment> findAllByUserId(Integer userId);
}
