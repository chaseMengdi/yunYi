package com.memory.yunyi.controller;

import com.memory.yunyi.entity.Log;
import com.memory.yunyi.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * 获取登录日志并返回
     *
     * @param model
     * @return
     */
    @GetMapping("log")
    public String log(Model model) {

        List<Log> list = logService.getAllLog();
        model.addAttribute("logs", list);
        return "log";
    }

}
