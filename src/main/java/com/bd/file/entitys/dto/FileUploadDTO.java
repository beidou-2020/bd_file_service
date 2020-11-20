package com.bd.file.entitys.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.InputStream;

@Data
@ToString
public class FileUploadDTO {

    /**
     * 源文件名
     */
    @NotEmpty(message = "上传文件的源名称不能为空")
    private String originalFilename;

    /**
     * 被上传文件的流对象
     */
    @NotNull(message = "上传文件的流对象不能为空")
    private InputStream inputStream;
}
