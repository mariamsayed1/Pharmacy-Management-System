package com.example.aswe.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User,Long>{
    User findByUsername(String username);

    User findByEmail(String email);
    User findById(Integer id);
}
