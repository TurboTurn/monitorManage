package com.monitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ys
 * @date : 2019/4/15 15:20 星期一
 **/

@RestController
public class DemoController {
	private Logger logger = LoggerFactory.getLogger(DemoController.class);
	@RequestMapping("demo")
	public String fun(String name){
		logger.info("传入参数为{}",name);
		return "this a demo!";
	}
}
