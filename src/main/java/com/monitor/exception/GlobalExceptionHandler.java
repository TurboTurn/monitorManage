package com.monitor.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * @author : ys
 * @date : 2019/10/11 19:52 星期五
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)  //申明捕获哪个异常类
	@ResponseBody  //返回给浏览器的是一个json格式，上面又没有@RestController，所以在此申明@ResponseBody
	public Map defaultExceptionHandler(Exception e) {
		Map<String, Object> result = new HashMap<>();
		result.put("errMessage", e.getMessage());
		logger.error("【系统异常】", e);
		return result;
	}
}
