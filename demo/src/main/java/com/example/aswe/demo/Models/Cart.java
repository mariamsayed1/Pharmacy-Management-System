package com.example.aswe.demo.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // private int cartQuantity;
    private Double TotalPrice;

    // @OneToMany
    // private List<Product> products;
    
    @ManyToOne
    private User user;

    @OneToMany
    private List<CartItem> items;


    public Cart() {
        this.items = new ArrayList<>();
    }

    public Cart(int id, List<CartItem> items) {
        this.id = id;
        this.items = items;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItem> getItems() {
        return this.items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public Cart id(int id) {
        setId(id);
        return this;
    }

    public Cart items(List<CartItem> items) {
        setItems(items);
        return this;
    }

    public Cart(int id, Double TotalPrice, User user, List<CartItem> items) {
        this.id = id;
        this.TotalPrice = TotalPrice;
        this.user = user;
        this.items = items;
    }

    public Double getTotalPrice() {
        return this.TotalPrice;
    }

    public void setTotalPrice(Double TotalPrice) {
        this.TotalPrice = TotalPrice;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Cart TotalPrice(Double TotalPrice) {
        setTotalPrice(TotalPrice);
        return this;
    }

    public Cart user(User user) {
        setUser(user);
        return this;
    }


    public void addItem(Product product) {
        if (items == null) {
            items = new ArrayList<>();  // Defensive coding: ensure items list is initialized
        }
        for (CartItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItem(product, 1));
    }

    public void removeItem(Product product) {
        if (items != null) {
            items.removeIf(item -> item.getProduct().getId() == product.getId());
        }
        // items.removeIf(item -> item.getProduct().getId() == product.getId());
    }

    public double getTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Cart)) {
            return false;
        }
        Cart cart = (Cart) o;
        return id == cart.id && Objects.equals(items, cart.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, items);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", items='" + getItems() + "'" +
            "}";
    }

    
}
