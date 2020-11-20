package com.bd.file.controller;

import com.bd.file.constant.FileConstant;
import com.bd.file.entitys.dto.FileUploadDTO;
import com.bd.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.InputStream;
import java.util.Objects;

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileController {

    @Resource
    private FileService fileService;

    @Autowired
    private FileConstant fileConstant;

    /**
     * 跳转到文件上传页面
     * @return
     */
    @RequestMapping(value = "/toIndex", method = RequestMethod.GET)
    public ModelAndView toIndex(){
        return new ModelAndView("index");
    }

    /**
     * 图片回显(访问文件服务器上的目标文件)
     * @return
     */
    @RequestMapping(value = "/fileEcho", method = RequestMethod.POST)
    public ModelAndView fileEcho(@RequestParam(name = "uploadFile", required = true) MultipartFile uploadFile){
        String fileTargetPath = fileService.uploadFile(fileConstant.getUploadServerPath(), uploadFile);
        return new ModelAndView("success").
                addObject("serverPath", fileConstant.getAccessPath()).
                addObject("name", fileTargetPath);
    }

    /**
     * 多文件上传
     * @param uploadFiles
     * @return
     */
    @PostMapping(value = "/uploadFiles")
    public String uploadFiles(@RequestParam(name = "uploadFiles", required = true) MultipartFile[] uploadFiles){
        String fileTargetPath = fileService.uploadFiles(fileConstant.getUploadServerPath(), uploadFiles);
        return fileTargetPath;
    }

    /**
     * 单文件上传
     * @param uploadFile
     * @return
     */
    @PostMapping(value = "/uploadFile")
    public String uploadFile(@RequestParam(name = "uploadFile", required = true) MultipartFile uploadFile){
        String fileTargetPath = fileService.uploadFile(fileConstant.getUploadServerPath(), uploadFile);
        return fileTargetPath;
    }

    /**
     * 以文件流方式写入指定服务器上的目录下
     * @return
     */
    @PostMapping("/fileStreamUpload")
    public String fileStreamUpload(@RequestBody @Valid FileUploadDTO fileUploadDTO){
        String originalFilename = fileUploadDTO.getOriginalFilename();
        InputStream inputStream = fileUploadDTO.getInputStream();
        if (Objects.isNull(inputStream)){
            log.error("文件流方式存储文件时流对象为空");
            return "";
        }
        String fileTargetPath = fileService.fileStreamUpload(fileConstant.getUploadServerPath(), originalFilename, inputStream);
        return fileTargetPath;
    }
}
