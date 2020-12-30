package com.bd.file.conf;

import com.bd.file.constant.FileConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import javax.servlet.MultipartConfigElement;


/**
 * 文件上传配置：
 *      1：文件存储到文件服务器的绝对路劲对应的虚拟路径，用于直接访问文件服务器上的资源。
 *      2：文件上传的资源大小限制。
 */
@Configuration
public class UploadFileConfig implements WebMvcConfigurer {

    @Autowired
    private FileConstant fileConstant;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //  /read/file/**为前端URL访问路径；后面的file:xxxx为本地磁盘映射
        registry.addResourceHandler(fileConstant.getStorageVirtualPath()).
                addResourceLocations(fileConstant.getUploadServerPath());
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 设置资源上传的单个资源的数据最大值
        factory.setMaxFileSize(DataSize.ofMegabytes(1l));       //1M
        // 设置资源上传的总数据最大值
        factory.setMaxRequestSize(DataSize.ofMegabytes(10l));   //10M
        return factory.createMultipartConfig();
    }

}
