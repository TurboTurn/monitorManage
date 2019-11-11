package com.monitor.beanConfig;


import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Topic;


/**
 * @author : ys
 * @date : 2019/4/15 19:44 星期一
 **/


@Configuration
public class ActiveMQConfig {
	@Bean(name = "tank")
	public Topic topic(){//球罐监控数据
		return new ActiveMQTopic("tank");
	}

	@Bean(name = "alarm")
	public Topic topic1(){//sis系统报警数据
		return new ActiveMQTopic("alarm");
	}
}

