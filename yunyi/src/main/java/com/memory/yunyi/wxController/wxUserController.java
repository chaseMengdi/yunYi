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
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSON;


import java.util.*;


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


    /**
     * 获取homepage用户列表以及用户主页
     *
     * @return
     */
    @GetMapping("/wxGetUserAndPageList")
    public Map<String,Object> getUserList() {
        //获取用户列表以及用户主页，并以openId排序，
        // 使map中users/userPages顺序对应
        List<User> users=userService.getAllUser();
        List<userPageContent> userPages=pageService.getAllContent();
        Collections.sort(users);
        Collections.sort(userPages);

        Map<String,Object> map=new HashMap<>();
        map.put("users",users);
        map.put("userPages",userPages);

        return map;
    }

    /**
     * 注册/登录结合，未注册则帮助用户注册
     * @param code wx.login获得的code
     * @param nickName wx.getUserInfo微信昵称
     * @param gender 1 男，0 女
     * @param avatarUrl 头像地址
     * @param province 省份
     * @param city 城市
     * @return 数据表中存在的注册用户
     */
    @PostMapping("/wxLogin")
    public User exist(@RequestParam(value = "code") String code,
                      @RequestParam(value = "nickName") String nickName,
                      @RequestParam(value = "gender") String gender,
                      @RequestParam(value = "avatarUrl") String avatarUrl,
                      @RequestParam(value = "province") String province,
                      @RequestParam(value = "city") String city) {

        String openId = userService.wxGetOpenId(code);
        User regUser;
        //用户未注册，先执行注册操作
        if (userService.existOrNot(openId) == 0) {
            regUser = new User(openId, nickName, Integer.valueOf(gender), avatarUrl, province, city);
            userService.reg(regUser);

            //用户注册时往visitInfo表添加默认数据
            VisitInfo v = new VisitInfo(regUser.getOpenId(), 0, 0, 0, 0);
            v.setUser(regUser);
            visitInfoService.add(v);
            //往pageContent表加入默认数据，默认模板号1
            userPageContent page = new userPageContent();
            page.setUserID(regUser.getOpenId());
            page.setModelID(1);
            page.setModelID(1);
            pageService.renew(page);
            //把模板1的使用量+1
            pageModelService.inc(1);
        } else {
            regUser = userService.findByOpenId(openId);
        }

        return regUser;
    }


////    用户注册
//    @PostMapping("/wxRegister")
//    public User reg(@RequestBody User user) {
//        //设置默认头像,注册用户
//        user.setAvatarUrl("../../imgs/timg.jpg");
//      User theUser= userService.reg(user);
//
//        //用户注册时往visitInfo表添加默认数据
//       VisitInfo v =  new VisitInfo(user.getOpenId(),0,0,0,0);
//       v.setUser(theUser);
//        visitInfoService.add(v);
//        //往pageContent表加入默认数据，默认模板号1
//        userPageContent page = new userPageContent();
//        page.setUserID(user.getUserID());
//        page.setModelID(1);
//        page.setModelID(1);
//        pageService.renew(page);
//        //把模板1的使用量+1
//        pageModelService.inc(1);
//        //新用户则返回注册后的对象
//        if(theUser!=null)
//        return userService.findByID(user.getUserID());
//        else //若用户id存在返回空
//            return null;
//    }

//    更新用户信息
    @PostMapping("/wxUpdate")
    public void update(@RequestBody User user){
        userService.update(user);
    }

}