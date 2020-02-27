package com.memory.yunyi.service;

import com.memory.yunyi.entity.userPageContent;
import com.memory.yunyi.repository.userPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class userPageServiceImpl implements userPageService {
    @Autowired
    private userPageRepository userPageRepository;

    @Override
    public List<userPageContent> pageListByHometown(String city) {
        return userPageRepository.pageListByHometown(city);
    }

    @Override
    public List<userPageContent> getAllContent() {

        return userPageRepository.findAll();
    }

    @Override
    public userPageContent findByOpenID(String id) {
        return userPageRepository.findByOpenId(id);
    }

    @Override
    public void deleteContent(Integer id) {
        userPageRepository.deleteById(id);
    }

    @Override
    public userPageContent renew(userPageContent userPageContent) {
        return userPageRepository.save(userPageContent);
    }

    @Override
    public void setModelId(Integer modelID, String userID) {
        userPageRepository.setModelId(modelID, userID);
    }
}
