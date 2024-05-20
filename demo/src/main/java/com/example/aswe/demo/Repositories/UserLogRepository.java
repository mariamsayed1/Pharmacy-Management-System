package com.example.aswe.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.aswe.demo.Models.UserLog;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
    // Custom query methods can be added here if needed
    List<UserLog> findByUserId(String userId);
}
