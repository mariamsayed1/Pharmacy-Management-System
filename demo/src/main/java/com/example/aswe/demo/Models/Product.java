package com.example.aswe.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int price;
    private String image;
    private String activeIngredient;
    private String sideEffect;
    private int quantity;

    @ManyToOne
    private Category category;


    public Product() {
    }

    public Product(int id, String name, int price, String image, String activeIngredient, String sideEffect, int quantity, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.activeIngredient = activeIngredient;
        this.sideEffect = sideEffect;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getActiveIngredient() {
        return this.activeIngredient;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public String getSideEffect() {
        return this.sideEffect;
    }

    public void setSideEffect(String sideEffect) {
        this.sideEffect = sideEffect;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product id(int id) {
        setId(id);
        return this;
    }

    public Product name(String name) {
        setName(name);
        return this;
    }

    public Product price(int price) {
        setPrice(price);
        return this;
    }

    public Product image(String image) {
        setImage(image);
        return this;
    }

    public Product activeIngredient(String activeIngredient) {
        setActiveIngredient(activeIngredient);
        return this;
    }

    public Product sideEffect(String sideEffect) {
        setSideEffect(sideEffect);
        return this;
    }

    public Product quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Product category(Category category) {
        setCategory(category);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && price == product.price && Objects.equals(image, product.image) && Objects.equals(activeIngredient, product.activeIngredient) && Objects.equals(sideEffect, product.sideEffect) && quantity == product.quantity && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image, activeIngredient, sideEffect, quantity, category);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", price='" + getPrice() + "'" +
            ", image='" + getImage() + "'" +
            ", activeIngredient='" + getActiveIngredient() + "'" +
            ", sideEffect='" + getSideEffect() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
    
}
