package com.monitor.beanConfig;

/*
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

*/
/**
 * @author : ys
 * @date : 2019/4/15 19:44 星期一
 **//*


@Configuration
public class ActiveMQConfig {
	@Bean
	public Queue queue(){
		return new ActiveMQQueue("queue");
	}
}
*/
