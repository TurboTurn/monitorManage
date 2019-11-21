package com.monitor.MQ;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : ys
 * @date : 2019/4/15 16:13 星期一
 * Spring Boot 内置了ActiveMQ 的服务，在主类声明Bean
 **/


@RestController
@RequestMapping("/mq")
public class MQController {
	private Logger logger = LoggerFactory.getLogger(MQController.class);

	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;

	//模拟前端通过Post请求传输数据，存储到kafka队列的其中一个主题topic，数据提供方直接通过页面填入数据即可
	//RequestBody接收传过来的数据
	//postman那边直接是put body（raw -> json）
	@PostMapping(path = "/kafka/send")
	public String sendKafka(@RequestBody String jsonString){
		kafkaTemplate.send("testTopic",jsonString);
		return "true";
	}


/*	@Autowired
	JmsTemplate jmsTemplate;//注入发送消息的对象
	@Resource(name = "tank")
	private Destination destination;

	@Autowired
	private TaskExecutor pushThreadPool;
	@RequestMapping("/testmq")
	public String t1(){//测试吞吐量
		pushThreadPool.execute(()->{
			long s = System.currentTimeMillis();
			for (int i=0;i<10000;i++){
				jmsTemplate.convertAndSend(destination,"msg");
			}
			System.out.println("耗时"+(System.currentTimeMillis()-s));
		});
		return "success";
	}


	*//**
	 * 生产数据到MQ，再由消费者将数据存入DB
	 * @param list
	 * @return
	 *//*
	@RequestMapping(path = "/produce" ,method = RequestMethod.POST)
	public String producer(@RequestBody Tank[] list){
		jmsTemplate.convertAndSend(destination,list);
		return "produce success";
	}*/

}

