package com.monitor.ActiveMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author : ys
 * @date : 2019/4/15 16:21 星期一
 **/

@Component
public class Consumer {
	private Logger logger = LoggerFactory.getLogger(Consumer.class);
	//接收消息的方法
	@JmsListener(destination = "queue")
	public void readMessage(String text){
		logger.info("收到消息：{}",text);
	}
}
