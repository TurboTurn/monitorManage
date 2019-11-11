package com.monitor.ActiveMQ;

import com.monitor.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @author : ys
 * @date : 2019/4/15 16:13 星期一
 * Spring Boot 内置了ActiveMQ 的服务，在主类声明Bean
 **/


@RestController
@RequestMapping("/queue")
public class QueueController {
	private Logger logger = LoggerFactory.getLogger(QueueController.class);

	//注入发送消息的对象
	@Autowired
	JmsTemplate jmsTemplate;
	@Resource(name = "tank")
	private Destination destination;


	//编写发送消息的方法

	@RequestMapping("send/{message}")
	public String send(@PathVariable String message) {
		User user = new User();
		user.setAge(20);
		user.setName(message);
		jmsTemplate.convertAndSend(destination,user);
		logger.info("发送消息：{}",user);
		return "消息发送成功！内容为： " + user;
	}

	@Autowired
	private TaskExecutor pushThreadPool;
	@RequestMapping("/testmq")
	public String t1(){
		pushThreadPool.execute(()->{
			long s = System.currentTimeMillis();
			for (int i=0;i<10000;i++){
				jmsTemplate.convertAndSend(destination,"msg");
			}
			System.out.println("耗时"+(System.currentTimeMillis()-s));
		});
		return "success";
	}
}

