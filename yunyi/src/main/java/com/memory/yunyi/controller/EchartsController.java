package com.memory.yunyi.controller;

import com.memory.yunyi.entity.Echarts;
import com.memory.yunyi.service.EchartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.annotation.RequestScope;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: 刘博谦
 * @Description: 图表控制器，控制生成各类图表
 * @Date: Created in 15:58 2020/2/18
 * @Modified By:
 */
@Controller
@RequestMapping("/")
public class EchartsController {
    @Autowired
    EchartsService echartsService;

    @GetMapping(value = "/echarts")
    public String echartTest(Model model) {
        System.err.println("========开始");
        return "echarts";
    }

    /**
     * 给html页面进行异步访问
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/EcharsShow")
    @ResponseBody
    public Map<String,List<Echarts>> findById(Model model) {
        Map<String,List<Echarts>> map=new HashMap<>();
        List<Echarts> userProvinceAndCount=echartsService.findUserProvinceAndCount();
        map.put("userProvinceAndCount",userProvinceAndCount);
        System.err.println(userProvinceAndCount.toString());
        return map;
    }
}
