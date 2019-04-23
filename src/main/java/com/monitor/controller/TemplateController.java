package com.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author : ys
 * @date : 2019/4/23 12:38 星期二
 **/
@Controller
public class TemplateController {
	@RequestMapping("/websocket")
	public String socket(Map<String,Object> map, HttpServletRequest request){
		String ip =request.getServerName();
		int port = request.getServerPort();
		System.out.println(ip);
		System.out.println(port);
		map.put("ip",ip+":"+port);
		return "websocket";
	}
}
