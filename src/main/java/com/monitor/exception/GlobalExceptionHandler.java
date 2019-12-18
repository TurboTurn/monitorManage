package com.monitor.exception;

import com.monitor.controller.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author : ys
 * @date : 2019/10/11 19:52 星期五
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)  //申明捕获哪个异常类
	@ResponseBody  //返回给浏览器的是一个json格式，上面又没有@RestController，所以在此申明@ResponseBody
	public ResultEntity defaultExceptionHandler(Exception e) {
		logger.error("【系统异常】:{}", e.getMessage());
		return ResultEntity.fail(e.getMessage());
	}
}
