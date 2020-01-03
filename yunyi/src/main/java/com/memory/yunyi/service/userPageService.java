package com.memory.yunyi.service;
import com.memory.yunyi.entity.userPageContent;
import java.util.List;
import java.util.Optional;


public interface userPageService {
    List<userPageContent> getAllContent();

    userPageContent findByID(Integer id);

    void deleteContent(Integer id);

    userPageContent renew(userPageContent userPageContent);

   void setModelId(Integer modelID,Integer userID);
}
