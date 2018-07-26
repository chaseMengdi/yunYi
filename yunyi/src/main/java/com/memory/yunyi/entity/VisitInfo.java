package com.memory.yunyi.entity;

import javax.persistence.*;

/*
 * by陈曦
 */

@Entity
public class VisitInfo {
    @Id
    Integer userID;
    Integer likeNum;
    Integer visitNum;
    Integer commentNum;
    Integer reportNum;

    public VisitInfo(){

    }
    public VisitInfo(Integer userID,Integer likeNum, Integer visitNum, Integer commentNum,Integer reportNum) {
        this.userID = userID;
        this.likeNum = likeNum;
        this.visitNum = visitNum;
        this.commentNum = commentNum;
        this.reportNum = reportNum;
    }

    @OneToOne
    private  User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getReportNum() {
        return reportNum;
    }

    public void setReportNum(Integer reportNum) {
        this.reportNum = reportNum;
    }

    public Integer getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(Integer visitNum) {
        this.visitNum = visitNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }
}
