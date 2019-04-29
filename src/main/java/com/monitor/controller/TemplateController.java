package com.monitor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * @author : ys
 * @date : 2019/4/23 12:38 星期二
 **/
@Controller
public class TemplateController {
	@RequestMapping("/websocket")
	public String socket(Map<String,Object> map, HttpServletRequest request) throws UnknownHostException {
//		String ip =request.getLocalName();
		String ip = InetAddress.getLocalHost().getHostAddress();
		int port = request.getLocalPort();
		System.out.println(ip+":"+port);
		map.put("ip",ip+":"+port);
		return "websocket";
	}
}
