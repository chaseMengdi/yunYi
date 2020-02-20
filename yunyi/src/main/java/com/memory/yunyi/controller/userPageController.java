package com.memory.yunyi.controller;

import com.memory.yunyi.entity.User;
import com.memory.yunyi.entity.process;
import com.memory.yunyi.entity.userPageContent;
import com.memory.yunyi.service.LogService;
import com.memory.yunyi.service.UserService;
import com.memory.yunyi.service.processService;
import com.memory.yunyi.service.userPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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

    /**
     * 进入页面修改用户主页
     *
     * @param id    用户openId
     * @param model
     * @return
     */
    @GetMapping("/changeUserHome/{id}")
    public String changePage(@PathVariable("id") String id, Model model) {
        userPageContent userPagecontent = userPageService.findByOpenID(id);
        model.addAttribute("usp", userPagecontent);
        process process2 = new process();
        model.addAttribute("prs", process2);
        return "changeUserHome";

    }

    /**
     * 删除模板，无用，准备移除
     *
     * @param id 模板id
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteData(@PathVariable("id") Integer id) {
        userPageService.deleteContent(id);
        return "redirect:/";

    }

    /**
     * 管理员对用户主页进行编辑，并保存修改
     *
     * @param userPageContent 用户主页内容
     * @param process0        管理员操作
     * @param model
     * @return
     */
    @PostMapping("/renew")
    public String renewPageData(userPageContent userPageContent, process process0, Model model) {
        userPageService.renew(userPageContent);

        process0.setAdminID(logService.findAdminNow());
        processService.add(process0);

        List<User> list = userService.getAllUser();
        model.addAttribute("users", list);
        return "userList";
    }

}
