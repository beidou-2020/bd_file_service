package com.bd.file;

import com.bd.file.constant.FileConstant;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class BdFileServiceApplicationTests {

    @Autowired
    private FileConstant fileConstant;

    @Test
    void filePathConstantValue() {
        log.info("文件上传路径：{}", fileConstant.getUploadServerPath());
        log.info("文件导出路径：{}", fileConstant.getExportClientPath());
    }

}
