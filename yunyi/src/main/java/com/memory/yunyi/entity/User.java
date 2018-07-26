package com.memory.yunyi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
/*
* by陈曦
**/
@Entity
public class User {
    @Id
    private Integer userID;
    private String name;
    private String hometown;
    private String livePlace;
    private String password;
    private String sex;
    private String status = "normal";
    private String avatar ;


    public User(){

    }
    public User(Integer userID,String name, String hometown,String livePlace, String password, String sex, String status) {
        this.userID = userID;
        this.name = name;
        this.hometown = hometown;
        this.livePlace = livePlace;
        this.password = password;
        this.sex = sex;
        this.status = status;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
