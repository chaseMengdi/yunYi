package com.memory.yunyi.entity;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class PageModel {

    @Id
    private Integer pageModelID;
    private String pageModelName;
    private String pictureLink;
    private Integer usageAmount;

    public PageModel(){

    }

    public PageModel(Integer pageModelID, String pageModelName, String pictureLink, Integer usageAmount) {
        this.pageModelID = pageModelID;
        this.pageModelName = pageModelName;
        this.pictureLink = pictureLink;
        this.usageAmount = usageAmount;
    }

    public Integer getPageModelID() {
        return pageModelID;
    }

    public void setPageModelID(Integer pageModelID) {
        this.pageModelID = pageModelID;
    }

    public String getPageModelName() {
        return pageModelName;
    }

    public void setPageModelName(String pageModelName) {
        this.pageModelName = pageModelName;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Integer getUsageAmount() {
        return usageAmount;
    }

    public void setUsageAmount(Integer usageAmount) {
        this.usageAmount = usageAmount;
    }

}
