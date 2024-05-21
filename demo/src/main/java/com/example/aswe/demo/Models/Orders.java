package com.example.aswe.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // private int customerId;
    private LocalDateTime orderDate;
    private String status;
    private Double totalAmount;
    
    @ManyToOne
    private User user;

    public Orders() {
    }

    public Orders(int id, LocalDateTime orderDate, String status, Double totalAmount, User user) {
        this.id = id;
        this.orderDate = orderDate;
        this.status = status;
        this.totalAmount = totalAmount;
        this.user = user;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Orders id(int id) {
        setId(id);
        return this;
    }

    public Orders orderDate(LocalDateTime orderDate) {
        setOrderDate(orderDate);
        return this;
    }

    public Orders status(String status) {
        setStatus(status);
        return this;
    }

    public Orders totalAmount(Double totalAmount) {
        setTotalAmount(totalAmount);
        return this;
    }

    public Orders user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Orders)) {
            return false;
        }
        Orders order = (Orders) o;
        return id == order.id && Objects.equals(orderDate, order.orderDate) && Objects.equals(status, order.status) && Objects.equals(totalAmount, order.totalAmount) && Objects.equals(user, order.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, status, totalAmount, user);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", orderDate='" + getOrderDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }

    

    }
