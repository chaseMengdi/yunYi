package com.memory.yunyi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class VisitInfo implements Comparable<VisitInfo> {
    @Id
    String userID;
    Integer likeNum;
    Integer visitNum;
    Integer commentNum;
    Integer reportNum;

    public VisitInfo() {

    }

    public VisitInfo(String userID, Integer likeNum, Integer visitNum, Integer commentNum, Integer reportNum) {
        this.userID = userID;
        this.likeNum = likeNum;
        this.visitNum = visitNum;
        this.commentNum = commentNum;
        this.reportNum = reportNum;
    }

    @OneToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
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

    @Override
    public String toString() {
        return "VisitInfo{" +
                "userID='" + userID + '\'' +
                ", likeNum=" + likeNum +
                ", visitNum=" + visitNum +
                ", commentNum=" + commentNum +
                ", reportNum=" + reportNum +
                ", user=" + user.toString() +
                '}';
    }

    @Override
    public int compareTo(VisitInfo other) {
        return this.userID.compareTo(other.getUserID());
    }
}
