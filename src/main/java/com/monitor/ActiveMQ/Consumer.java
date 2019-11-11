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
//	接收消息的方法
	@JmsListener(destination = "tank")
	public void readMessage(String text){//球罐数据存储到influxDB
//		logger.info("消费者一：收到消息：{}",text);
	}

//	@JmsListener(destination = "tank")
//	public void readMessage1(ObjectMessage message) throws JMSException {//自定义报警信息
//		logger.info("消费者二：收到消息：{}",(User)message.getObject());
//	}
}
