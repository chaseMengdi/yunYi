package com.memory.yunyi.wxController;
import com.memory.yunyi.entity.PageModel;
import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.entity.userPageContent;
import com.memory.yunyi.service.PageModelService;
import com.memory.yunyi.service.UserService;
import com.memory.yunyi.entity.User;
import com.memory.yunyi.service.VisitInfoService;
import com.memory.yunyi.service.userPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/")
public class wxUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private VisitInfoService visitInfoService;
    @Autowired
    private userPageService pageService;
    @Autowired
    private PageModelService pageModelService;

//    获取用户列表
    @GetMapping("/wxGetUserList")
    public List<User> getUserList() {
        List<User> list = userService.getAllUser();
        return list;
    }

//    登录验证，密码错误返回空，正确返回这条用户数据
    @PostMapping("/wxLogin")
    public User exist(@RequestBody User user) {
        return userService.login(user.getUserID(), user.getPassword());
    }


//    用户注册
    @PostMapping("/wxRegister")
    public User reg(@RequestBody User user) {
        //设置默认头像,注册用户
        user.setAvatar("../../imgs/timg.jpg");
      User theUser= userService.reg(user);

        //用户注册时往visitInfo表添加默认数据
       VisitInfo v =  new VisitInfo(user.getUserID(),0,0,0,0);
       v.setUser(theUser);
        visitInfoService.add(v);
        //往pageContent表加入默认数据，默认模板号1
        userPageContent page = new userPageContent();
        page.setUserID(user.getUserID());
        page.setModelID(1);
        pageService.renew(page);
        //把模板1的使用量+1
        pageModelService.inc(1);
        //新用户则返回注册后的对象
        if(theUser!=null)
        return userService.findByID(user.getUserID());
        else //若用户id存在返回空
            return null;
    }

//    更新用户信息
    @PostMapping("/wxUpdate")
    public void update(@RequestBody User user){
        userService.update(user);
    }

}