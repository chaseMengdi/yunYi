package com.memory.yunyi.wxController;

import com.memory.yunyi.entity.userPageContent;
import com.memory.yunyi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
public class wxUpgController {
    @Autowired
    private userPageService pageService;
    @Autowired
    private UserService userService;
    @Autowired
    private VisitInfoService visitInfoService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private PageModelService pageModelService;


    /**
     * 一次性拿到用户主页所需要的全部数据
     * 括主页内容，用户个人信息，主页统计信息，评论列表
     *
     * @param id 用户openid
     * @return
     */
    @PostMapping("/wxGetUpgById")
    public Map<String, Object> get(@RequestParam String id) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content", pageService.findByOpenID(id));
        map.put("user", userService.findByOpenId(id));
        map.put("visitInfo", visitInfoService.findByOpenId(id));
        map.put("comment", commentService.listByTimeForOne(id));
        return map;
    }


    /**
     * 当用户做了修改以后，保存主页内容
     *
     * @param u 用户主页信息
     * @return
     */
    @PostMapping("/wxSaveContent")
    public userPageContent save(@RequestBody userPageContent u) {
        return pageService.renew(u);
    }


    /**
     * 用户选择模板后设定模板号
     *
     * @param u 用户主页信息
     */
    @PostMapping("/setModel")
    public void set(@RequestBody userPageContent u) {
        //旧模板使用人数-1，先由传来的页面内容的openid获取数据库对应的模板号
        pageModelService.dec(pageService.findByOpenID(u.getUserID()).getModelID());
        //新模板使用人数+1，再讲传来的页面内容模板号更新到数据库
        pageModelService.inc(u.getModelID());
        pageService.setModelId(u.getModelID(), u.getUserID());
    }
}
