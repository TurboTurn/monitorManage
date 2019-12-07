package com.monitor.mq;

import com.alibaba.fastjson.JSONObject;
import com.monitor.pojo.EmailInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * 用来监听邮件队列,并将其中的消息发送到指定的邮箱
 *
 * @author Created by Divo
 * @date 2019/11/20
 */
//@Component
public class EmailConsumer {
	private Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

	private final JavaMailSenderImpl javaMailSender;

	public EmailConsumer(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@KafkaListener(topics = "emailTopic")
	public void onMessage(String message) {
		EmailInfo emailInfo = JSONObject.parseObject(message, EmailInfo.class);
		logger.info("邮件详细内容" + emailInfo.toString());
		//发送邮件给通知人
		sendAlarmMail(emailInfo);
	}

	public void sendAlarmMail(EmailInfo emailInfo) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(emailInfo.getFrom());
		simpleMailMessage.setTo(emailInfo.getTo());
		simpleMailMessage.setSubject(emailInfo.getSubject());
		simpleMailMessage.setText(emailInfo.getMsg());
		javaMailSender.send(simpleMailMessage);
	}
}
