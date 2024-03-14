package com.example.aswe.demo.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findAllByCategoryId(int id);
}
