package com.example.aswe.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>{

    
}
