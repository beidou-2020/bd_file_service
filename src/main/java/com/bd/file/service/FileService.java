package com.bd.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileService {
	
	/**
	 * 上传文件到服务器(多文件)
	 * @param
	 * @return
	 */
	String uploadFiles(String storagePath, MultipartFile[] uploadFiles);
	
	/**
	 * 上传文件到服务器(单文件)
	 * @param storagePath
	 * @param uploadFile
	 * @return
	 */
	String uploadFile(String storagePath, MultipartFile uploadFile);

	/**
	 * 以文件流方式写入指定服务器上的目录下
	 * @param storagePath
	 * @param uploadFile
	 * @return
	 */
	String fileStreamUpload(String storagePath, String originalFilename, InputStream uploadFile);

}
