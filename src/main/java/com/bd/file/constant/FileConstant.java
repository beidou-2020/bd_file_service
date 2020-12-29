package com.bd.file.constant;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@ToString
public class FileConstant {

    /**
     * 保存到文件服务器上的目的路径
     */
    @Value("${filepath.uploadServerPath}")
    private String uploadServerPath;

    /**
     * 文件服务器存储资源的绝对路径对应的虚拟路径(用于UploadFileConfig配置文件)
     */
    @Value("${filepath.storageVirtualPath}")
    private String storageVirtualPath;

    /**
     * 文件服务器存储资源的绝对路径对应的虚拟路径(用于前端访问)
     */
    @Value("${filepath.accessPath}")
    private String accessPath;

    /**
     * 文件导出到客户端的本地存储路径
     */
    /*@Value("${filepath.exportClientPath}")
    private String exportClientPath;*/
}
