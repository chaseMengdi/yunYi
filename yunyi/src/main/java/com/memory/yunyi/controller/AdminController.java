package com.memory.yunyi.controller;



import com.memory.yunyi.entity.Admin;
import com.memory.yunyi.entity.User;
import com.memory.yunyi.service.AdminService;
import com.memory.yunyi.entity.Log;
import com.memory.yunyi.service.LogService;
import com.memory.yunyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.lang.String;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private LogService logService;
    @Autowired
    private UserService userService;

    @GetMapping
    public String login(){
        return "login";
    }

    @GetMapping("/login1")
    public String login1(){
        return "login";
    }

    @PostMapping("/login")
    public String judge(@RequestParam("adminID")Integer adminID, @RequestParam("password") String password, Model model) {
        Admin admin=adminService.adminLoginJudge(adminID,password);
        boolean flag;
        Log log = new Log();
        String str;
        String adminName;
        String logTime;
        if(admin!=null) {
            adminName=adminService.findAdminNameByID(adminID);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            logTime = df.format(new Date());
            log.setAdminID(adminID);
            log.setAdminName(adminName);
            log.setLogTime(logTime);
            logService.addLog(log);
            List<User> list = userService.getAllUser();
            model.addAttribute("users",list);
            str= "userList";
        }else {
            flag = false;
            model.addAttribute("flag",flag);
            str="login";
        }
        return str;
    }


}
