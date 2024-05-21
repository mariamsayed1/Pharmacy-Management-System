package com.example.aswe.demo.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartQuantity;
    private Double TotalPrice;

    @OneToMany
    private Product product;

    public Cart(){
    }

    public Cart(int cartQuantity,Double TotalPrice){
        this.cartQuantity=cartQuantity;
        this.TotalPrice=TotalPrice;
    }

    public int getCartQuantity() {
        return this.cartQuantity;
    }

    public void setCartQuantity(int cartQuantity) {
        this.cartQuantity = cartQuantity;
    }

    public Double getTotalPrice() {
        return this.TotalPrice;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    @Override
    public String toString(){
        return "{" +
            " cartQuantity='" + getCartQuantity() + "'" +
            ", TotalPrice='" + getTotalPrice() + "'" +
            "}";
    }
}
