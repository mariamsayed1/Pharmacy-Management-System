package com.example.aswe.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.aswe.demo.Models.Pharmacist;



public interface PharmacistRepository extends JpaRepository<Pharmacist, Integer>{
    Pharmacist findByUsername(String username);
    
}
