package com.bd.file.utils;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result {
	
	/**
	 * 自定义响应状态
	 */
	private Integer code;
	
	/**
	 * 自定义异常信息(供前端使用)
	 */
	private String customizeMsg;
	
	/**
	 * 服务器异常信息(打印到日志，供工程师定位问题使用)
	 */
	private String serverErrorMsg;
}
