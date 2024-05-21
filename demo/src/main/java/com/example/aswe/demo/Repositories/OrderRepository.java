package com.example.aswe.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.Orders;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
}
