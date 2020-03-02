package com.memory.yunyi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class util {

    private static String uploadPath;

    @Value("${upload-path}")
    public  void setUploadPath(String uploadPath) {
        util.uploadPath = uploadPath;
    }

    //base64编码图片转MultipartFile类型
    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStrs = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStrs[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return new BASE64DecodedMultipartFile(b, baseStrs[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //上传图片
    public static String uploadImg(MultipartFile file) throws UnknownHostException {
        if(file.isEmpty()) {
            return "上传失败";
        }
        System.out.println(uploadPath);
        String filePath = new String(uploadPath);//图片上传位置
        String fileName = System.currentTimeMillis() + file.getOriginalFilename();//生成图片名
        File dest = new File(filePath + fileName);//上传图片路径
        if(!new File(filePath).exists()){
            new File(filePath).mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            System.out.println(e);
        }
        return  "http://"+ InetAddress.getLocalHost().getHostAddress() +":8080/upload/" + fileName;
//        return  "http://203.195.156.107:8080"+"/upload/" + fileName;
    }
}
