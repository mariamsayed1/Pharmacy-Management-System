package com.example.aswe.demo.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.aswe.demo.Models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findAllByCategoryId(int id);
    Page<Product> findAllByCategoryId(int categoryId, Pageable pageable);
    Product findById(int id);
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(concat(?1, '%'))")
    List<Product> findByNameStartingWithIgnoreCase(String prefix);
}
