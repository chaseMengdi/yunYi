package com.memory.yunyi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 刘博谦
 * @Description: 上传文件后返回的信息类
 * @Date: Created in 20:28 2020/4/9
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadMsg {
    private Integer status;

    private String msg;

    private String filePath;
}
