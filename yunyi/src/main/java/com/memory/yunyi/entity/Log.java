package com.memory.yunyi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Log {

    @Id
    @GeneratedValue
    private Integer logID;
    private Integer adminID;
    private String adminName;
    private String logTime;

    public Log() {
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

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public Log(Integer logID, Integer adminID, String adminName, String logTime) {
        this.logID = logID;
        this.adminID = adminID;
        this.adminName = adminName;
        this.logTime = logTime;
    }
}