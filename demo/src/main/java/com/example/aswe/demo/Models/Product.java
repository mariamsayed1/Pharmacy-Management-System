package com.example.aswe.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String image;
    private String activeIngredient;
    private String sideEffect;
    private String description;
    private int quantity;
    private LocalDate prodDate;
    private LocalDate expDate;

    @ManyToOne
    private Category category;


    public Product() {
    }

    public Product(int id, String name, double price, String image, String activeIngredient, String sideEffect, String description, int quantity, LocalDate prodDate, LocalDate expDate, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.activeIngredient = activeIngredient;
        this.sideEffect = sideEffect;
        this.description = description;
        this.quantity = quantity;
        this.prodDate = prodDate;
        this.expDate = expDate;
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

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
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

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getProdDate() {
        return this.prodDate;
    }

    public void setProdDate(LocalDate prodDate) {
        this.prodDate = prodDate;
    }

    public LocalDate getExpDate() {
        return this.expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
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

    public Product price(double price) {
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

    public Product description(String description) {
        setDescription(description);
        return this;
    }

    public Product quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    public Product prodDate(LocalDate prodDate) {
        setProdDate(prodDate);
        return this;
    }

    public Product expDate(LocalDate expDate) {
        setExpDate(expDate);
        return this;
    }

    public Product category(Category category) {
        setCategory(category);
        return this;
    }


    public boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean isPriceValid(double price){
        return getPrice() > 0;
    }
    public boolean isQuantityValid(int quantity){
        return getQuantity() > 0;
    }

    public boolean isValidDate(LocalDate prodDate, LocalDate expDate){
        return prodDate.isBefore(expDate);
    }

    
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Product)) {
            return false;
        }
        Product product = (Product) o;
        return id == product.id && Objects.equals(name, product.name) && price == product.price && Objects.equals(image, product.image) && Objects.equals(activeIngredient, product.activeIngredient) && Objects.equals(sideEffect, product.sideEffect) && Objects.equals(description, product.description) && quantity == product.quantity && Objects.equals(prodDate, product.prodDate) && Objects.equals(expDate, product.expDate) && Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, image, activeIngredient, sideEffect, description, quantity, prodDate, expDate, category);
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
            ", description='" + getDescription() + "'" +
            ", quantity='" + getQuantity() + "'" +
            ", prodDate='" + getProdDate() + "'" +
            ", expDate='" + getExpDate() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }
    
}