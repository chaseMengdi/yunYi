package com.memory.yunyi.controller;

import com.memory.yunyi.entity.User;
import com.memory.yunyi.service.UserService;
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

    /**
     * 获取用户列表并展示
     *
     * @param model
     * @return
     */
    @GetMapping("/userList")
    public String index(Model model) {
        List<User> list = userService.getAllUser();
        model.addAttribute("users", list);
        return "userList";
    }

    /**
     * 获取版本号，无用，准备移除
     *
     * @return
     */
    @GetMapping("/version")
    public String getVersion() {
        return "The current version 1.0.";
    }


    /**
     * 封禁用户
     *
     * @param id    用户openid
     * @param model
     * @return
     */
    @GetMapping("/user/stop/{id}")
    public String stopUser(@PathVariable("id") String id, Model model) {
        userService.stopByID(id);
        List<User> list = userService.getAllUser();
        model.addAttribute("users", list);
        return "redirect:/userList";
    }


    /**
     * 解封用户
     *
     * @param id    用户openid
     * @param model
     * @return
     */
    @GetMapping("/user/recovery/{id}")
    public String recoveryByID(@PathVariable("id") String id, Model model) {
        userService.recoveryByID(id);
        List<User> list = userService.getAllUser();
        model.addAttribute("users", list);
        return "redirect:/userList";
    }

    /**
     * 根据nickname模糊查询用户
     *
     * @param name  用户nickName
     * @param model
     * @return
     */
    @PostMapping("/userList")
    public String getUserQuery(@RequestParam("name") String name, Model model) {
        List<User> list = userService.queryByName("%" + name + "%");
        model.addAttribute("users", list);
        return "userList";
    }

}
