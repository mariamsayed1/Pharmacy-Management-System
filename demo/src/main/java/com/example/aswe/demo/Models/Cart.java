package com.example.aswe.demo.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.List;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private int cartQuantity;
    private Double TotalPrice;

    @OneToMany
    private List<Product> products;
    
    @OneToOne
    private User user;


    public Cart() {
    }

    public Cart(int ID, int cartQuantity, Double TotalPrice, List<Product> products, User user) {
        this.ID = ID;
        this.cartQuantity = cartQuantity;
        this.TotalPrice = TotalPrice;
        this.products = products;
        this.user = user;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart ID(int ID) {
        setID(ID);
        return this;
    }

    public Cart cartQuantity(int cartQuantity) {
        setCartQuantity(cartQuantity);
        return this;
    }

    public Cart TotalPrice(Double TotalPrice) {
        setTotalPrice(TotalPrice);
        return this;
    }

    public Cart products(List<Product> products) {
        setProducts(products);
        return this;
    }

    public Cart user(User user) {
        setUser(user);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart cart = (Cart) o;
        return ID == cart.ID && cartQuantity == cart.cartQuantity && Objects.equals(TotalPrice, cart.TotalPrice) && Objects.equals(products, cart.products) && Objects.equals(user, cart.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, cartQuantity, TotalPrice, products, user);
    }

    @Override
    public String toString() {
        return "{" +
            " ID='" + getID() + "'" +
            ", cartQuantity='" + getCartQuantity() + "'" +
            ", TotalPrice='" + getTotalPrice() + "'" +
            ", products='" + getProducts() + "'" +
            ", user='" + getUser() + "'" +
            "}";
    }
    
}
