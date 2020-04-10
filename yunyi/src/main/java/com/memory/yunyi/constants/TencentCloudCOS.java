package com.memory.yunyi.constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author: 刘博谦
 * @Description: 腾讯云COS配置变量，从yml配置文件注入
 * @Date: Created in 20:33 2020/4/9
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "tencent-cloud")
public class TencentCloudCOS {
    private String accessKey;

    private String secretKey;

    private String bucket;

    private String bucketName;

    private String path;

    private String prefix;
}
