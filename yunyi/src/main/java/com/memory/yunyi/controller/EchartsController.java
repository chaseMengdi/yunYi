package com.memory.yunyi.controller;

import com.memory.yunyi.entity.Echarts;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 刘博谦
 * @Description: 图表控制器，控制生成各类图表
 * @Date: Created in 15:58 2020/2/18
 * @Modified By:
 */
@Controller
public class EchartsController {
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
    public List<Echarts> findById(Model model) {
        List<Echarts> list = new ArrayList<Echarts>();
        list.add(new Echarts("帽子", 50));
        list.add(new Echarts("鞋子", 126));
        list.add(new Echarts("毛衣", 75));
        list.add(new Echarts("羽绒服", 201));
        list.add(new Echarts("羊毛衫", 172));
        System.err.println(list.toString());

        return list;
    }
}
