package com.memory.yunyi.wxController;

import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.service.UserService;
import com.memory.yunyi.service.VisitInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/")
public class wxLikeController {
    @Autowired
    private VisitInfoService visitInfoService;
    @Autowired
    private UserService userService;

    /**
     * 某一用户点赞总数-1
     *
     * @param id 用户openId
     * @return 用户主页访问信息
     */
    @PostMapping("/wxDecLikeById")
    public VisitInfo decById(@RequestParam String id) {
        return visitInfoService.decLike(id);
    }

    /**
     * 将某一用户点赞总数+1
     *
     * @param id 用户opneId
     * @return
     */
    @PostMapping("/wxLikeById")
    public VisitInfo incById(@RequestParam String id) {
        return visitInfoService.incLike(id);
    }


    /**
     * 将某一用户被举报总数+1
     *
     * @param id 用户opneId
     * @return
     */
    @PostMapping("/wxReportById")
    public VisitInfo reportById(@RequestParam String id) {
        return visitInfoService.incReport(id);
    }


    /**
     * 读取用户列表按点赞数排列
     *
     * @return
     */
    @GetMapping("/wxDescListByLike")
    public List<VisitInfo> listByLike() {
        return visitInfoService.DescByLike();
    }


}