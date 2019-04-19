package com.monitor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author : ys
 * @date : 2019/4/19 13:44 星期五
 * webSocket与springBoot集成的配置
 **/
@Configuration
public class WebConfig {
	@Bean
	public ServerEndpointExporter serverEE(){
		return new ServerEndpointExporter();
	}
}