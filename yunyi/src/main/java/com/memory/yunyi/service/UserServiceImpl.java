package com.memory.yunyi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.memory.yunyi.entity.User;
import com.memory.yunyi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


    @Override
    public void stopByID(String id) {
        userRepository.stopByID(id);
    }

    @Override
    public void recoveryByID(String id) {
        userRepository.recoverByID(id);
    }

    @Override
    public User findByOpenId(String openId) {
        return userRepository.findByOpenId(openId);
    }

    @Override
    public List<User> queryByName(String name) {
        return userRepository.queryByName(name);
    }

//    @Override
//    public User login(Integer id, String pwd) {
//        return userRepository.login(id,pwd);
//    }

    @Override
    public User reg(User user) {
        return userRepository.save(user);
    }

    @Override
    public void update(User user) {
        userRepository.update(user.getOpenId(), user.getNickName(), user.getAvatarUrl());
    }

    @Override
    public Integer existOrNot(String openid) {
        return userRepository.existsOrNotByOpenId(openid);
    }

    @Override
    public String wxGetOpenId(String code) {
        String WX_URL = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        //替换appid，appsecret，和code
        String requestUrl = WX_URL.replace("APPID", "wx3c26f5c92a626a3f").
                replace("SECRET", "34e8564b742d7bcb9683b5fa5eae6c89").replace("JSCODE", code).
                replace("authorization_code", "authorization_code");

        //调用get方法发起get请求，并把返回值赋值给returnvalue
        String returnvalue = GET(requestUrl);
        System.out.println("请求的网址：" + requestUrl);
        System.out.println("返回的值：" + returnvalue);
        //定义一个json对象。
        JSONObject convertvalue = new JSONObject();
        //将得到的字符串转换为json
        convertvalue = (JSONObject) JSON.parse(returnvalue);
        System.out.println("return openid is ：" + convertvalue.get("openid"));
        System.out.println("return sessionkey is ：" + convertvalue.get("session_key"));

        return (String) convertvalue.get("openid");
    }

    //发起get请求的方法。
    @Override
    public String GET(String url) {
        String result = "";
        BufferedReader in = null;
        InputStream is = null;
        InputStreamReader isr = null;
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.connect();
            Map<String, List<String>> map = conn.getHeaderFields();
            is = conn.getInputStream();
            isr = new InputStreamReader(is);
            in = new BufferedReader(isr);
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (is != null) {
                    is.close();
                }
                if (isr != null) {
                    isr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

//    @Override
//    public List<Object> wxGetUserAndPageList() {
//        return userRepository.wxGetUserAndPageList();
//    }
}
