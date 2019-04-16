/*
package com.monitor.ActiveMQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Destination;

*/
/**
 * @author : ys
 * @date : 2019/4/15 16:13 星期一
 * Spring Boot 内置了ActiveMQ 的服务，在主类声明Bean
 **//*


@RestController
@RequestMapping("queue")
public class QueueController {
	private Logger logger = LoggerFactory.getLogger(QueueController.class);
	//注入发送消息的对象
	private final JmsTemplate jmsTemplate;
	JmsMessagingTemplate jmsMessagingTemplate;
	//注入消息队列 interface Queue extends Destination
	private final Destination destination;

	public QueueController(JmsTemplate jmsTemplate, Destination destination) {
		this.jmsTemplate = jmsTemplate;
		this.destination = destination;
	}

	//编写发送消息的方法

	@RequestMapping("send/{message}")
	public String send(@PathVariable String message) {
		this.jmsTemplate.convertAndSend(destination,message);
		logger.info("发送消息：{}",message);
		return "消息发送成功！内容为： "+message;
	}

}
*/
