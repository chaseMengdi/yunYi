package com.memory.yunyi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.memory.yunyi.service.processService;
@Controller
@RequestMapping
public class processController {
    @Autowired
    private processService processService;

}
