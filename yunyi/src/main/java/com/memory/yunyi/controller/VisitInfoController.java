package com.memory.yunyi.controller;

import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.service.VisitInfoService;
import com.memory.yunyi.service.VisitInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class VisitInfoController {
    @Autowired
    private VisitInfoService visitInfoService;

    @GetMapping("/likesCount")
    public  String like(Model model){
        List<VisitInfo> list = visitInfoService.DescByLike();
        model.addAttribute("usersByLike",list);
        return "likesCount";
    }

    @GetMapping("/complainCount")
    public  String report(Model model){
        List<VisitInfo> list = visitInfoService.DescByReport();
        model.addAttribute("usersByComplain",list);
        return "complainCount";
    }

}
