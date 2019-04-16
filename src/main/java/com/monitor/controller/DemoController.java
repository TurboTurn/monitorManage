package com.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ys
 * @date : 2019/4/15 15:20 星期一
 **/
@CrossOrigin
@RestController
public class DemoController {
	private Logger logger = LoggerFactory.getLogger(DemoController.class);

	double data = 10.50f;

	@RequestMapping("demo")
	public String fun(String name){
		logger.info("传入参数为{}",name);
		return "this a demo!";
	}

	@RequestMapping("/data")
	public String fun1(){//返回String时不能执行toFixed
		data += 1.5;
		String s = String.format("%.2f",data);
		logger.info("返回值为{}",s);
		return s;
	}
	@RequestMapping("/data1")
	public double fun2(){
		data += 1.5;
		logger.info("返回值为{}",data);
		return data;
	}


}
