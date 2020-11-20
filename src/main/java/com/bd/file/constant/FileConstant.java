package com.bd.file.constant;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:application.properties")
@ConfigurationProperties(prefix = "filepath")
@Data
@ToString
public class FileConstant {

    /**
     * 文件上传到文件服务器的存储路径
     */
    private String uploadServerPath;

    /**
     * 文件导出到客户端的本地存储路径
     */
    private String exportClientPath;

    /**
     * 文件服务器存储资源的绝对路径对应的虚拟路径(用于UploadFileConfig配置文件)
     */
    private String storageVirtualPath;

    /**
     * 文件服务器存储资源的绝对路径对应的虚拟路径(用于前端访问)
     */
    private String accessPath;
}
