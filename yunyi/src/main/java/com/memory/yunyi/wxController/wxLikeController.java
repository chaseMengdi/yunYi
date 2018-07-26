package com.memory.yunyi.wxController;
import com.memory.yunyi.service.UserService;
import com.memory.yunyi.entity.User;
import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.service.VisitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
by陈曦
 */
@RestController
@RequestMapping("/")
public class wxLikeController {
    @Autowired
    private VisitInfoService visitInfoService;
    @Autowired
    private UserService userService;

//将某一用户点赞总数-1
    @PostMapping("/wxDecLikeById")
    public VisitInfo decById(@RequestBody Integer id) {
        return visitInfoService.decLike(id);
    }
    //将某一用户点赞总数+1
    @PostMapping("/wxLikeById")
    public VisitInfo incById(@RequestBody Integer id) {
        return visitInfoService.incLike(id);
    }
//将某一用户被举报总数+1
    @PostMapping("/wxReportById")
    public VisitInfo reportById(@RequestBody Integer id) {
        return visitInfoService.incReport(id);
    }
//读取用户列表按点赞数排列
    @GetMapping("/wxDescListByLike")
    public List<VisitInfo> listByLike() {
        return visitInfoService.DescByLike();
    }
//筛选与用户相同的用户，按点赞数排列
    @PostMapping("/wxListByHometown")
    public List<VisitInfo> listByHometown(@RequestBody Integer id) {
        return visitInfoService.ListByHometown(userService.findByID(id).getHometown());
    }



}