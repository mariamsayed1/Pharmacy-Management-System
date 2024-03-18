package com.example.aswe.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;
@Entity
public class Pharmacist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String usertype;

    public Pharmacist() {
    }

    public Pharmacist(Long id, String username, String password, String usertype) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.usertype = usertype;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return this.usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public Pharmacist id(Long id) {
        setId(id);
        return this;
    }

    public Pharmacist username(String username) {
        setUsername(username);
        return this;
    }

    public Pharmacist password(String password) {
        setPassword(password);
        return this;
    }

    public Pharmacist usertype(String usertype) {
        setUsertype(usertype);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Pharmacist)) {
            return false;
        }
        Pharmacist pharmacist = (Pharmacist) o;
        return Objects.equals(id, pharmacist.id) && Objects.equals(username, pharmacist.username) && Objects.equals(password, pharmacist.password) && Objects.equals(usertype, pharmacist.usertype);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, usertype);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", username='" + getUsername() + "'" +
            ", password='" + getPassword() + "'" +
            ", usertype='" + getUsertype() + "'" +
            "}";
    }
    
}
