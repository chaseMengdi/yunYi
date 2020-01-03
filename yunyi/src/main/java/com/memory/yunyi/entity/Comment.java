package com.memory.yunyi.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer cmtID;
    private Integer commenterID;
    private String commenterName;
    private Integer ownerID;
    private String  text;
    private String  time;

    public  Comment() {
    }
    public Comment(Integer cmtID, Integer commenterID, Integer ownerID, String text, String time) {
        this.cmtID = cmtID;
        this.commenterID = commenterID;
        this.ownerID = ownerID;
        this.text = text;
        this.time = time;
    }

    public Integer getCmtID() {
        return cmtID;
    }

    public void setCmtID(Integer cmtID) {
        this.cmtID = cmtID;
    }

    public Integer getCommenterID() {
        return commenterID;
    }

    public void setCommenterID(Integer commenterID) {
        this.commenterID = commenterID;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public Integer getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(Integer ownerID) {
        this.ownerID = ownerID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
