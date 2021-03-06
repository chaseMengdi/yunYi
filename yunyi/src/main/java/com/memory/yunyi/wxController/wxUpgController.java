package com.memory.yunyi.wxController;

import com.memory.yunyi.constants.TencentCloudCOS;
import com.memory.yunyi.entity.UploadMsg;
import com.memory.yunyi.entity.userPageContent;
import com.memory.yunyi.service.*;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/")
public class wxUpgController {
    @Autowired
    private TencentCloudCOS tencentCloudCOS;
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

    /**
     * 上传图片接口
     * @param file 参数名称
     * @return
     */
    @RequestMapping("/uploadImg")
    public UploadMsg upLoadImg(@RequestParam("file") MultipartFile file) throws UnknownHostException {
        if (file == null) {
            return new UploadMsg(400, "文件为空", null);
        }
        String oldFileName = file.getOriginalFilename();
        String eName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID() + eName;
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DATE);
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(tencentCloudCOS.getAccessKey(), tencentCloudCOS.getSecretKey());
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(tencentCloudCOS.getBucket()));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
        String bucketName = tencentCloudCOS.getBucketName();

        // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20 M 以下的文件使用该接口
        // 大文件上传请参照 API 文档高级 API 上传
        File localFile = null;
        try {
            localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 指定要上传到 COS 上的路径
            String key = "/" + tencentCloudCOS.getPrefix() + "/" + year + "-" + month + "-" + day + "/" + newFileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
            PutObjectResult putObjectResult = cosclient.putObject(putObjectRequest);
            return new UploadMsg(200, "图片成功上传至服务器。", tencentCloudCOS.getPath() + putObjectRequest.getKey());
        } catch (IOException e) {
            return new UploadMsg(400, e.getMessage(), null);
        } finally {
            // 关闭客户端(关闭后台线程)
            cosclient.shutdown();
        }
        
        
    }
}
