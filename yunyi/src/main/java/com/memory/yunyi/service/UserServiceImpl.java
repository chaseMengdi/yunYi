package com.memory.yunyi.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.memory.yunyi.repository.UserRepository;
import com.memory.yunyi.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

/*
 * by陈曦
 */

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }



    @Override
    public void stopByID(Integer id) {
        userRepository.stopByID(id);
    }

    @Override
    public void recoveryByID(Integer id) {
        userRepository.recoverByID(id);
    }

    @Override
    public User findByID(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> queryByName(String name) {
        return userRepository.queryByName(name);
    }

    @Override
    public User login(Integer id, String pwd) {
        return userRepository.login(id,pwd);
    }

    @Override
    public User reg(User user) {
        if(userRepository.existsById(user.getUserID()))
        {
            return null;
        }
        else
            return userRepository.save(user);
    }

    @Override
    public void update(User user) {
      userRepository.update(user.getUserID(),user.getName(),user.getHometown(),user.getPassword(),user.getAvatar());
    }
}
