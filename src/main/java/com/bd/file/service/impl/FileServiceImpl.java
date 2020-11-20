package com.bd.file.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import com.bd.file.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

	
	@Override
	public String uploadFiles(String storagePath, MultipartFile[] uploadFiles) {
		try {
			StringBuffer picNameBuffer = new StringBuffer();		//存储到DB中的文件名
			for (int i = 0; i < uploadFiles.length; i++) {
				MultipartFile fileByIndex = uploadFiles[i];
				String uploadFileName = fileByIndex.getOriginalFilename();		// 获取文件名(例如：XXX.jpg)
				if (StringUtils.isEmpty(uploadFileName)) {
					//文件名为空判定为没有上传对象
					log.info("多文件上传时第{}个文件的文件名为空：{}", i, uploadFileName);
					continue;
				}
				String sourceFileName = uploadFileName.substring(0, uploadFileName.lastIndexOf("."));	//获取不含后缀名的源文件名
				String fileSuffixName = uploadFileName.substring(uploadFileName.lastIndexOf("."));	//获取后缀名
				StringBuffer sBuffer = new StringBuffer();
				Date date = new Date();
				sBuffer.append(sourceFileName).append("_").append(date.getTime()).append(fileSuffixName);
				String targetFileName = sBuffer.toString();  	//保存到目标服务器上的文件名
				//将生成的新文件名追加到picNameBuffer
				picNameBuffer.append(targetFileName);
				
				//开始保存文件到服务器
				File targetFile = new File(storagePath + targetFileName);
				// 如果目标文件存储的路径不存在，则逐级创建
				if (!targetFile.getParentFile().exists()) {
					targetFile.getParentFile().mkdirs();
				}
				// 将内存数据写入磁盘
				fileByIndex.transferTo(targetFile);
			}
			
			return picNameBuffer.toString();
		} catch (Exception e) {
			log.error("上传多文件异常：{}", ExceptionUtils.getFullStackTrace(e));
		}
		return "";
	}

	@Override
	public String uploadFile(String storagePath, MultipartFile uploadFile) {
		try {
			String uploadFileName = uploadFile.getOriginalFilename();		// 获取文件名(例如：XXX.jpg)
			if (StringUtils.isEmpty(uploadFileName) || uploadFile.isEmpty()) {
				//文件名为空判定为没有上传对象
				log.info("单文件上传时文件名为空：{}", uploadFileName);
				return "";
			}
			String sourceFileName = uploadFileName.substring(0, uploadFileName.lastIndexOf("."));	//获取不含后缀名的源文件名
			String fileSuffixName = uploadFileName.substring(uploadFileName.lastIndexOf("."));	//获取后缀名
			StringBuffer sBuffer = new StringBuffer();
			Date date = new Date();
			sBuffer.append(sourceFileName).append("_").append(date.getTime()).append(fileSuffixName);
			String targetFileName = sBuffer.toString();  	//保存到目标服务器上的文件名
			
			//开始保存文件到服务器
			File targetFile = new File(storagePath + targetFileName);
			// 如果目标文件存储的路径不存在，则逐级创建
			if (!targetFile.getParentFile().exists()) {
				targetFile.getParentFile().mkdirs();
			}
			
			// 将内存数据写入磁盘
			uploadFile.transferTo(targetFile);
			return targetFileName;
		} catch (IllegalStateException | IOException e) {
			log.error("上传文件异常：{}", ExceptionUtils.getFullStackTrace(e));
		}
		
		return "";
	}

	@Override
	public String fileStreamUpload(String storagePath, String originalFilename, InputStream uploadFile) {
		String sourceFileName = originalFilename.substring(0, originalFilename.lastIndexOf("."));	//获取不含后缀名的源文件名
		String fileSuffixName = originalFilename.substring(originalFilename.lastIndexOf("."));	//获取后缀名
		StringBuffer sBuffer = new StringBuffer();
		Date date = new Date();
		sBuffer.append(sourceFileName).append("_").append(date.getTime()).append(fileSuffixName);
		String targetFileName = sBuffer.toString();  	//保存到目标服务器上的文件名

		//开始保存文件到服务器
		File targetFile = new File(storagePath + targetFileName);
		// 如果目标文件存储的路径不存在，则逐级创建
		if (!targetFile.getParentFile().exists()) {
			targetFile.getParentFile().mkdirs();
		}

		FileOutputStream fos = null;
		InputStream stream = null;
		try {
			fos = new FileOutputStream(targetFile);
			stream = uploadFile;
			byte[] buffer = new byte[1024*1024];
			int byteRead = 0;
			while((byteRead = stream.read(buffer))!=-1){
				fos.write(buffer, 0, byteRead);
				fos.flush();
			}
		} catch (Exception e) {
			log.error("文件流写入异常：{}", ExceptionUtils.getFullStackTrace(e));
			return "";
		}finally{
			try {
				if(fos!=null){
					fos.close();
				}
				if(stream!=null){
					stream.close();
				}
			} catch (Exception e) {
				log.error("文件流关闭异常：{}", ExceptionUtils.getFullStackTrace(e));
				return "";
			}
		}

		return targetFileName;
	}
}
