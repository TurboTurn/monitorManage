package com.monitor.MQ;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monitor.pojo.EmailInfo;
import com.monitor.pojo.Tank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

/**
 * 用来监听邮件队列,并将其中的消息发送到指定的邮箱
 * @author Created by Divo
 * @date 2019/11/20
 */
@Component
public class EmailConsumer {
    private Logger logger = LoggerFactory.getLogger(EmailConsumer.class);

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @KafkaListener(topics = "emailTopic")
    public void onMessage(String message){

        EmailInfo emailInfo = JSONObject.parseObject(message,EmailInfo.class);
		logger.info("输出emailTopic监听到的内容"+emailInfo.toString());

		//发送消息到邮件
		sendAlarmMail(emailInfo);

  }

    public void sendAlarmMail(EmailInfo emailInfo){
        //简单邮件
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(emailInfo.getFrom());
        simpleMailMessage.setTo(emailInfo.getTo());
        simpleMailMessage.setSubject(emailInfo.getSubject());
        simpleMailMessage.setText(emailInfo.getMsg());
        javaMailSender.send(simpleMailMessage);
    }
}
