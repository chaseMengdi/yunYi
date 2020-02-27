package com.memory.yunyi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User implements Comparable<User> {
    @Id
    private String openId;
    private String nickName;
    private Integer gender;
    private String avatarUrl;
    private String province;
    private String city;
    private String status = "normal";


    public User() {
        super();
    }

    public User(String openId, String nickName, Integer gender, String avatarUrl, String province, String city) {
        this.openId = openId;
        this.nickName = nickName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
        this.province = province;
        this.city = city;
        this.status = "normal";
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "openId='" + openId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * 排序则按照openId进行排序，用于小程序list页面展示
     * @param other
     * @return
     */
    @Override
    public int compareTo(User other) {
        return this.openId.compareTo(other.getOpenId());
    }
}
