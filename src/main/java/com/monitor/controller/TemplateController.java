package com.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : ys
 * @date : 2019/4/23 12:38 星期二
 **/
@Controller
public class TemplateController {
	@GetMapping("/gauge")
	public String socket(Map<String,Object> map, HttpServletRequest request)  {
		return "gauge";
	}

	@GetMapping("/testException")
	public Integer test() {//模拟异常，使用全局异常处理器
		int a = 1 / 0;
		return a;
	}
}
