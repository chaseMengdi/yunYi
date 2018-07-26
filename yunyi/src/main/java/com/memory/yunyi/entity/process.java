package com.memory.yunyi.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//By罗子璇
@Entity
public class process {
    @Id
    @GeneratedValue
    private Integer ID;
    private Integer adminID;
    private String reasonNum;
    private String ttime;
    private Integer userID;

    public process(Integer ID,Integer adminID, String reasonNum, String ttime, Integer userID) {
        this.ID = ID;
        this.adminID = adminID;
        this.reasonNum = reasonNum;
        this.ttime = ttime;
        this.userID = userID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getAdminID() {
        return adminID;
    }

    public void setAdminID(Integer adminID) {
        this.adminID = adminID;
    }

    public String getReasonNum() {
        return reasonNum;
    }

    public void setReasonNum(String reasonNum) {
        this.reasonNum = reasonNum;
    }

    public String getTime() {
        return ttime;
    }

    public void setTime(String ttime) {
        this.ttime = ttime;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public process() {
    }

}
