package com.memory.yunyi.service;

import com.memory.yunyi.entity.User;

import java.util.List;


public interface UserService {

    List<User> getAllUser();

    void stopByID(String id);

    void recoveryByID(String id);

    User findByOpenId(String openId);

    List<User> queryByName(String name);

    //    User login(Integer id,String pwd);
    User reg(User user);

    void update(User user);

    String wxGetOpenId(String code);

    String GET(String url);

    Integer existOrNot(String openid);

//    List<Object> wxGetUserAndPageList();
}
