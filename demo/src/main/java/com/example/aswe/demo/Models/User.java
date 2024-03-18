package com.example.aswe.demo.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullname;
    private String username;
    private String email;
    private String phonenumber;
    private String password;
    private String confirmpassword;
    private String usertype;
    public User() {
    }

    public User(Long id, String fullname, String username, String email, String phonenumber, String password, String confirmpassword, String usertype) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.email = email;
        this.phonenumber = phonenumber;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.usertype = usertype;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsertype() {
        return this.usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public User id(Long id) {
        setId(id);
        return this;
    }

    public User usertype(String usertype) {
        setUsertype(usertype);
        return this;
    }


    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return this.confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public User fullname(String fullname) {
        setFullname(fullname);
        return this;
    }

    public User username(String username) {
        setUsername(username);
        return this;
    }

    public User email(String email) {
        setEmail(email);
        return this;
    }

    public User phonenumber(String phonenumber) {
        setPhonenumber(phonenumber);
        return this;
    }

    public User password(String password) {
        setPassword(password);
        return this;
    }

    public User confirmpassword(String confirmpassword) {
        setConfirmpassword(confirmpassword);
        return this;
    }

    public boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    public boolean isPasswordValid(String password, String confirmPassword) {
        return password != null && password.length() >= 8 && password.equals(confirmPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(fullname, user.fullname) && Objects.equals(username, user.username) && Objects.equals(email, user.email) && phonenumber == user.phonenumber && Objects.equals(password, user.password) && Objects.equals(confirmpassword, user.confirmpassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, username, email, phonenumber, password, confirmpassword);
    }

    @Override
    public String toString() {
        return "{" +
            " fullname='" + getFullname() + "'" +
            ", username='" + getUsername() + "'" +
            ", email='" + getEmail() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", password='" + getPassword() + "'" +
            ", confirmpassword='" + getConfirmpassword() + "'" +
            "}";
    }
    
}