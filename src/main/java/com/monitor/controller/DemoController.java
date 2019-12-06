package com.monitor.controller;

import com.monitor.dao.TableDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author : ys
 * @date : 2019/4/15 15:20 星期一
 **/
@CrossOrigin
@RestController
@RequestMapping("/demo")
public class DemoController {
	@Autowired
	TableDao tableDao;

	private Logger logger = LoggerFactory.getLogger(DemoController.class);

	double data = 10.50f;

	@RequestMapping("demo")
	public String fun(String name) {//echart.html
		logger.info("传入参数为{}", name);
		int[] a = {10, 12, 14, 13, 15, 12, 14};
		return Arrays.toString(a);
	}

	@GetMapping("/data")
	public String fun1() {//返回String时不能执行toFixed
		data += 1.5;
		String s = String.format("%.2f", data);
		logger.info("返回值为{}", s);
		return s;
	}


}
