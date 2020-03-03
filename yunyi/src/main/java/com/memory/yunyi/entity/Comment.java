package com.memory.yunyi.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Integer cmtID;
    private String commenterID;
    private String commenterName;
    private String ownerID;
    private String text;
    private String time;
    private String avatarUrl;

    public Comment() {
        super();
    }

    public Comment(String commenterID, String commenterName, String ownerID, String text, String time, String avatarUrl) {
        this.commenterID = commenterID;
        this.commenterName = commenterName;
        this.ownerID = ownerID;
        this.text = text;
        this.time = time;
        this.avatarUrl=avatarUrl;
    }

    public Integer getCmtID() {
        return cmtID;
    }

    public void setCmtID(Integer cmtID) {
        this.cmtID = cmtID;
    }

    public String getCommenterID() {
        return commenterID;
    }

    public void setCommenterID(String commenterID) {
        this.commenterID = commenterID;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
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

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
