package com.example.aswe.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    
}
