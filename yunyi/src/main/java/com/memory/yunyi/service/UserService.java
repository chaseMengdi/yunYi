package com.memory.yunyi.service;

import com.memory.yunyi.entity.User;
import java.util.List;



public interface UserService {

     List<User> getAllUser();
    void  stopByID(Integer id);
    void  recoveryByID(Integer id);
    User  findByID(Integer id);
    List<User> queryByName(String name);
    User login(Integer id,String pwd);
    User reg(User user);
    void update(User user);
}
