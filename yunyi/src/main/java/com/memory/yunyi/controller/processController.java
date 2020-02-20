package com.memory.yunyi.controller;

import com.memory.yunyi.service.processService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class processController {
    @Autowired
    private processService processService;

}
