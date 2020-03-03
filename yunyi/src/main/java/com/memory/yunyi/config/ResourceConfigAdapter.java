package com.memory.yunyi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: 刘博谦
 * @Description: 资源映射配置
 * @Date: Created in 21:04 2020/3/2
 * @Modified By:
 */
@Configuration
public class ResourceConfigAdapter implements WebMvcConfigurer {
    @Value("${upload-path}")
    private String uploadPath;

    //添加资源访问映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+ uploadPath);
    }
}
