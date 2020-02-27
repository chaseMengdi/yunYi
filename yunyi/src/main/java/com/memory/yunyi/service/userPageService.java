package com.memory.yunyi.service;

import com.memory.yunyi.entity.userPageContent;

import java.util.List;


public interface userPageService {
    List<userPageContent> getAllContent();

    List<userPageContent> pageListByHometown(String city);

    userPageContent findByOpenID(String id);

    void deleteContent(Integer id);

    userPageContent renew(userPageContent userPageContent);

    void setModelId(Integer modelID, String userID);
}
