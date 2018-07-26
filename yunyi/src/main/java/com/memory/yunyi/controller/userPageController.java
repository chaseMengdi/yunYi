package com.memory.yunyi.controller;

import com.memory.yunyi.entity.User;
import com.memory.yunyi.service.LogService;
import com.memory.yunyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.memory.yunyi.service.userPageService;
import org.springframework.ui.Model;
import com.memory.yunyi.entity.userPageContent;
import com.memory.yunyi.entity.process;
import com.memory.yunyi.entity.Admin;

import java.util.List;
import java.util.Optional;

import com.memory.yunyi.service.processService;

@Controller
@RequestMapping("/")
public class userPageController {
    @Autowired
    private userPageService userPageService;
    @Autowired
    private processService processService;

    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @GetMapping("/changeUserHome/{id}")
    public String changePage(@PathVariable("id") Integer id, Model model){
        userPageContent userPagecontent = userPageService.findByID(id);
        model.addAttribute("usp",userPagecontent);
        process process2=new process();
        model.addAttribute("prs",process2);
        return "changeUserHome";

    }
    @GetMapping("/delete/{id}")
    public String deleteData(@PathVariable("id") Integer id) {
        userPageService.deleteContent(id);
        return "redirect:/";

    }
    @PostMapping("/renew")
    public String renewPageData(userPageContent userPageContent,process process0,Model model){
        userPageService.renew(userPageContent);

        process0.setAdminID(logService.findAdminNow());
        processService.add(process0);

        List<User> list = userService.getAllUser();
        model.addAttribute("users",list);
        return "userList";
    }

}
