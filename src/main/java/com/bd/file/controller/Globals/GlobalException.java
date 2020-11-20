package com.bd.file.controller.Globals;

import com.bd.file.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * 全局统一异常处理类
 */
//控制器增强:ControllerAdvice已经被@Component修饰，则说明标记@ControllerAdvice会被扫描到容器中(看源码)。
@ControllerAdvice //(spring3.2带来的新特性)
@Slf4j
public class GlobalException {

	/**
	 * 文件上传中资源数据超过设置的最大限制
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseBody
	public String uploadSizeExceptionHandler(Exception exception) {
		log.error("服务器错误: {}", ExceptionUtils.getFullStackTrace(exception));
		Result result = new Result();
		result.setCode(5000);
		result.setCustomizeMsg("服务器出错：上传资源的大小超过最大限制数");
		result.setServerErrorMsg(exception.getMessage());
		return JSONObject.toJSONString(result);
	}
	

	/**
	 * 统一处理程序运行时产生的异常到error日志文件中
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String errorExceptionHandler(Exception exception) {
		//将运行时异常的堆栈信息写入到error日志文件中
		log.error("服务器错误: {}", ExceptionUtils.getFullStackTrace(exception));
		Result result = new Result();
		result.setCode(5000);
		result.setCustomizeMsg("服务器出错：请联系接口工程师");
		result.setServerErrorMsg(exception.getMessage());
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 统一处理接口参数传递问题(针对@RequestParam注解)到error日志文件中
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseBody
	public String parameterExceptionHandler(MissingServletRequestParameterException ex) {
		//将运行时异常的堆栈信息写入到error日志文件中
		log.error("客户端错误: {}", ExceptionUtils.getFullStackTrace(ex));
		Result result = new Result();
		result.setCode(4000);
		result.setCustomizeMsg("客户端传递参数错误：请联系前端工程师");
		result.setServerErrorMsg(ex.getMessage());
		return JSONObject.toJSONString(result);
	}
	
	/**
	 * 统一处理接口参数传递问题(针对@Valid注解)到error日志文件中
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	public String validExceptionHandler(MethodArgumentNotValidException ex) {
		//将运行时异常的堆栈信息写入到error日志文件中
		log.error("客户端错误: {}", ExceptionUtils.getFullStackTrace(ex));
		Result result = new Result();
		result.setCode(4001);
		result.setCustomizeMsg("客户端传递参数错误：请联系前端工程师");
		//获取@NotNull注解上自定义的message信息
		result.setServerErrorMsg(ex.getBindingResult().getFieldError().getDefaultMessage());
		return JSONObject.toJSONString(result);
	}

}
