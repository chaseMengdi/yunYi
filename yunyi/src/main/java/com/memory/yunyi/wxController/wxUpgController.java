package com.memory.yunyi.wxController;
import com.memory.yunyi.entity.PageModel;
import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.service.*;
import com.memory.yunyi.entity.userPageContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
by陈曦
 */

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


//    一次性拿到用户主页所需要的全部数据
//    包括主页内容，用户个人信息，主页统计信息，评论列表
    @PostMapping("/wxGetUpgById")
    public Map<String,Object> get(@RequestBody Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("content",pageService.findByID(id));
        map.put("user",userService.findByID(id));
        map.put("visitInfo",visitInfoService.findById(id));
        map.put("comment",commentService.listByTimeForOne(id));
        return map;
  }

//  当用户做了修改以后，保存主页内容
  @PostMapping("/wxSaveContent")
  public userPageContent save(@RequestBody userPageContent u){
      return pageService.renew(u);
          }

//   用户选择模板后设定模板号
   @PostMapping("/setModel")
    public void set(@RequestBody userPageContent u){
        pageModelService.dec( pageService.findByID(u.getUserID()).getModelID() );
        pageModelService.inc(u.getModelID());
         pageService.setModelId(u.getModelID(),u.getUserID());
   }
}
