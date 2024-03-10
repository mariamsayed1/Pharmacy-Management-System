package com.example.aswe.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.User;

public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);
}
