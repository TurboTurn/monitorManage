package com.monitor.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monitor.pojo.*;
import com.monitor.service.AlarmHistoryService;
import com.monitor.service.AlarmRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.util.*;


/**
 * @author Created by Divo
 * @date 2019/11/18
 */
//@Component
public class AlarmConsumer {

	private Logger logger = LoggerFactory.getLogger(AlarmConsumer.class);

	@Autowired
	private AlarmRuleService alarmService;
	@Autowired
	private AlarmHistoryService alarmHistoryService;

	private Map<String, AlarmRule> alarmRuleMap = new HashMap<>();

	private String from = "164497083@qq.com";

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@PostConstruct
	public void init() {
		System.out.println("读取报警规则");
		List<AlarmRule> alarmRuleList = alarmService.selectAll();
		alarmRuleList.forEach(alarmRule -> alarmRuleMap.put(alarmRule.getTankID(), alarmRule));
	}

	int count = 0;
	@KafkaListener(topics = "tankTopic", containerFactory = "containerFactory2")
	public void onMessage(String message) {
		List<Tank> list = JSONObject.parseArray(message, Tank.class);//将json转化为Tank数组
//		logger.info(message);
		count++;
		System.out.println("报警模块收到数据" + count);
		//根据读到的数据进行一一对比
//		if(4>2)
//			return;
		for (Tank tank : list) {
			if (alarmRuleMap.containsKey(tank.getA1_tank())) {
				AlarmRule alarmRule = alarmRuleMap.get(tank.getA1_tank());//获得该油罐的报警规则

				//触发报警
				if (tank.getHeight_ld() > alarmRule.getHeightH() ||
						tank.getPressure() > alarmRule.getPressureH() ||
						tank.getTemperature() > alarmRule.getTemperatureH()) {
					//存储报警记录
					AlarmHistory alarmHistory = new AlarmHistory();
					alarmHistory.setTankId(alarmRule.getTankID());
					alarmHistory.setAlarmRuleId(alarmRule.getId());
					alarmHistory.setAlarmRule(JSON.toJSONString(alarmRule));//报警规则内容
					alarmHistory.setAlarmTime(new Date());
					alarmHistory.setAlarmData(JSON.toJSONString(tank));//触发报警数据
					alarmHistoryService.insertAlarmHistory(alarmHistory);

					//发送报警通知
					if(alarmRule.getNotifyType() == 1){//短信

					}else if(alarmRule.getNotifyType() == 2){//邮件
						String subject = String.format("%s %s油罐危险报警",tank.getA1_tank(), tank.getA2_oil());
						String msg = String.format("%s %s油罐发生危险报警\n触发的报警规则信息为：\n%s\n触发报警的监控数据为%s",
								tank.getA1_tank(), tank.getA2_oil(),JSON.toJSONString(alarmRule),JSON.toJSONString(tank));
						EmailInfo emailInfo = new EmailInfo();
						emailInfo.setFrom(from);
						emailInfo.setTo(alarmRule.getNotifier());
						emailInfo.setSubject(subject);
						emailInfo.setMsg(msg);
						System.out.println(emailInfo);//发送邮件
//						kafkaTemplate.send("emailTopic", JSON.toJSONString(emailInfo));

						//todo存储发送消息记录
					}
				}
			}
		}
	}

}
