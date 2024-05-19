package com.example.aswe.demo.Models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.Objects;

@Entity
public class UserLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userId;
    private Date loginTime;
    private String pageVisited;

    public UserLog() {}

    public UserLog(Long id, String userId, Date loginTime, String pageVisited) {
        this.id = id;
        this.userId = userId;
        this.loginTime = loginTime;
        this.pageVisited = pageVisited;
    }

    // Getters and Setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return this.loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getPageVisited() {
        return this.pageVisited;
    }

    public void setPageVisited(String pageVisited) {
        this.pageVisited = pageVisited;
    }

    public UserLog id(Long id) {
        setId(id);
        return this;
    }

    public UserLog userId(String userId) {
        setUserId(userId);
        return this;
    }

    public UserLog loginTime(Date loginTime) {
        setLoginTime(loginTime);
        return this;
    }

    public UserLog pageVisited(String pageVisited) {
        setPageVisited(pageVisited);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UserLog)) {
            return false;
        }
        UserLog userLog = (UserLog) o;
        return Objects.equals(id, userLog.id) && Objects.equals(userId, userLog.userId) && Objects.equals(loginTime, userLog.loginTime) && Objects.equals(pageVisited, userLog.pageVisited);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, loginTime, pageVisited);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", loginTime='" + getLoginTime() + "'" +
            ", pageVisited='" + getPageVisited() + "'" +
            "}";
    }
}
