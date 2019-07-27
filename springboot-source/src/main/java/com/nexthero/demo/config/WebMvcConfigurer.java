package com.nexthero.demo.config;

import com.nexthero.demo.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * WebMvcConfigurer
 *
 * @version 1.0
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //和页面有关的静态目录都放在项目的static目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        //上传的图片在项目目录下的upload-dir目录下，访问路径如：http://localhost:8080/upload-dir/Taco-cloud.jpg
        //其中upload-dir表示访问的前缀。"file:C:/Users/David/Documents/HBuilderProjects/nextHero/springboot-source/upload-dir/"是文件真实的存储路径
        registry.addResourceHandler("/upload-dir/**").addResourceLocations("file:" + storageProperties.getLocation() + "/");
    }
}