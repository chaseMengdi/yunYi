package com.memory.yunyi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Admin {

    @Id
    @GeneratedValue
    private Integer adminID;
    private String adminName;
    private String password;


    public Admin() {
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(Integer adminID, String adminName, String password) {
        this.adminID = adminID;
        this.adminName = adminName;
        this.password = password;
    }
}

