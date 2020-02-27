package com.memory.yunyi.wxController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.memory.yunyi.entity.User;
import com.memory.yunyi.entity.VisitInfo;
import com.memory.yunyi.entity.userPageContent;
import com.memory.yunyi.service.PageModelService;
import com.memory.yunyi.service.UserService;
import com.memory.yunyi.service.VisitInfoService;
import com.memory.yunyi.service.userPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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


    /**
     * 获取homepage用户列表/用户主页信息/用户主页访问信息
     *
     * @return
     */
    @GetMapping("/wxGetUserAndPageList")
    public JSONArray getUserList() {
        //获取用户列表以及用户主页，并以openId排序，
        List<userPageContent> userPages = pageService.getAllContent();
        List<VisitInfo> userVisitInfos = visitInfoService.getAllVisitInfos();

        Collections.sort(userPages);
        Collections.sort(userVisitInfos);

        //组合成JSON数据，方便前端访问
        JSONArray level1Array = new JSONArray();
        for (int i = 0; i < userPages.size(); ++i) {

            JSONObject level2Object = new JSONObject();
            level2Object.put("userPageContent", userPages.get(i));
            level2Object.put("VisitInfo", userVisitInfos.get(i));
            level1Array.add(level2Object);
        }

        return level1Array;
    }

    /**
     * 筛选与用户相同家乡(city)的用户列表/用户主页信息/用户主页访问信息，按点赞数排列
     *
     * @param id 用户openId
     * @return
     */
    @PostMapping("/wxListByHometown")
    public JSONArray listByHometown(@RequestParam String id) {
        List<VisitInfo> visitInfos = visitInfoService.infoListByHometown(userService.findByOpenId(id).getCity());
        List<userPageContent> userPageContents = pageService.pageListByHometown(userService.findByOpenId(id).getCity());

        Collections.sort(visitInfos);
        Collections.sort(userPageContents);

        //组合成JSON数据，方便前端访问
        JSONArray level1Array = new JSONArray();
        for (int i = 0; i < visitInfos.size(); ++i) {
            JSONObject level2Object = new JSONObject();
            level2Object.put("userPageContent", userPageContents.get(i));
            level2Object.put("VisitInfo", visitInfos.get(i));
            level1Array.add(level2Object);
        }

        return level1Array;
    }

    /**
     * 注册/登录结合，未注册则帮助用户注册
     *
     * @param code      wx.login获得的code
     * @param nickName  wx.getUserInfo微信昵称
     * @param gender    1 男，0 女
     * @param avatarUrl 头像地址
     * @param province  省份
     * @param city      城市
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


    /**
     * 将对应openId的用户的nickName、avatarUrl更新
     *
     * @param user
     */
    @PostMapping("/wxUpdate")
    public void update(@RequestBody User user) {
        userService.update(user);
    }

}