package com.example.aswe.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer>{
    
}
