package com.memory.yunyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.memory.yunyi.repository.userPageRepository;
import com.memory.yunyi.entity.userPageContent;

import java.util.List;
import java.util.Optional;

//by罗子璇
@Service
public class userPageServiceImpl implements userPageService {
    @Autowired
    private userPageRepository userPageRepository;

    @Override
    public List<userPageContent> getAllContent() {

        return userPageRepository.findAll();
    }

    @Override
    public userPageContent findByID(Integer id) {
        return userPageRepository.findById(id).get();
    }

    @Override
    public void deleteContent(Integer id) {
        userPageRepository.deleteById(id);
    }

    @Override
    public userPageContent renew(userPageContent userPageContent){
        return userPageRepository.save(userPageContent);
    }

    @Override
    public void setModelId(Integer modelID, Integer userID) {
         userPageRepository.setModelId(modelID,userID);
    }
}
