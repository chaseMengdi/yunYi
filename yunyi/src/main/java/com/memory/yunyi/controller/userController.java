package com.memory.yunyi.controller;

import com.memory.yunyi.service.UserService;
import com.memory.yunyi.service.UserServiceImpl;
import com.memory.yunyi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/")
public class userController {

    @Autowired
    private UserService userService;

//获取用户列表
    @GetMapping("/userList")
    public  String index(Model model){
        List<User> list = userService.getAllUser();
        model.addAttribute("users",list);
        return "userList";
    }

    //获取版本号
    @GetMapping("/version")
    public String getVersion(){
        return "The current version 1.0.";
    }


//    封禁用户
    @GetMapping("/user/stop/{id}")
    public String stopUser(@PathVariable("id") Integer id,Model model){
        userService.stopByID(id);
        List<User> list = userService.getAllUser();
        model.addAttribute("users",list);
        return "redirect:/userList";
    }

//    解封用户
    @GetMapping("/user/recovery/{id}")
    public String recoveryByID(@PathVariable("id") Integer id,Model model){
        userService.recoveryByID(id);
        List<User> list = userService.getAllUser();
        model.addAttribute("users",list);
        return "redirect:/userList";
    }

//    根据昵称查找用户
    @PostMapping("/userList")
    public String getUserQuery(@RequestParam("name") String name,Model model){
        List<User> list = userService.queryByName("%"+name+"%");
        model.addAttribute("users",list);
        return "userList";
    }

}
