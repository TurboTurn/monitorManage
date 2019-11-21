package com.monitor.MQ;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monitor.pojo.*;
import com.monitor.service.AlarmRecordService;
import com.monitor.service.AlarmService;
import com.monitor.service.UserService;
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
@Component
public class AlarmConsumer {

	private Logger logger = LoggerFactory.getLogger(AlarmConsumer.class);

	@Autowired
	private AlarmService alarmService;

	@Autowired
	private UserService userService;

	@Autowired
	private AlarmRecordService alarmRecordService;

	List<Alarm> alarmList;
	List<UserInfo> userInfoList;

	Map<String, Alarm> alarmMap = new HashMap<>();
	Map<String, String> emailMap = new HashMap<>();

	private String email = "164497083@qq.com";
	private String subject = "数据异常";

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	@PostConstruct
	public void init() {
		alarmList = alarmService.selectAll();
		alarmList.forEach(a -> alarmMap.put(a.getTank(), a));
		//从mysql数据库去读规则(alarm)
		System.out.println("<-----------打印数据库得到的报警规则------------->");
		alarmList.forEach(alarm -> System.out.println(alarm.toString()));
		userInfoList = userService.selectAll();
		userInfoList.forEach(userInfo -> emailMap.put(userInfo.getUsername(), userInfo.getEmail()));
	}


	@KafkaListener(topics = "testTopic")
	public void onMessage(String message) {
		List<Tank> list = JSONObject.parseArray(message, Tank.class);//将json转化为Tank数组
//        logger.info(message);

		//根据读到的数据进行一一对比
		Alarm alarm;
		StringBuilder stringBuilder = new StringBuilder();

		for (Tank tank : list) {
			if (alarmMap.containsKey(tank.getA1_tank())) {

				AlarmRecord alarmRecord = new AlarmRecord();


				boolean Presssure_flag, Height_flag, Height_ld_flag, Height_sf1_flag, Height_sf2_flag, Temperature_flag;

				alarm = alarmMap.get(tank.getA1_tank());

				Presssure_flag = (alarm.getPressure() < tank.getPressure());
				Height_ld_flag = (alarm.getHeight() < tank.getHeight_ld());
				Height_sf1_flag = (alarm.getHeight() < tank.getHeight_sf1());
				Height_sf2_flag = (alarm.getHeight() < tank.getHeight_sf2());

				Height_flag = (Height_ld_flag || Height_sf1_flag || Height_sf2_flag);
				Temperature_flag = (alarm.getTemperature() < tank.getTemperature());

				if (Presssure_flag || Height_flag || Temperature_flag) {
					/**
					 * 设置违反了那一条规则
					 */
					alarmRecord.setAlarmId((alarm.getId()));
					/**
					 * 设置警报创建时间
					 */
					alarmRecord.setHappendTime(new Date(tank.getTime()));
					alarmRecord.setTank(tank.getA1_tank());
					alarmRecord.setOil(tank.getA2_oil());

					stringBuilder.append(tank.getA1_tank() + " ").append(tank.getA2_oil() + " ");
					if (Presssure_flag) {
						/**
						 * alarm表的各个字段记录超标数
						 */
						alarmRecord.setPressure(tank.getPressure() - alarm.getPressure());
						stringBuilder.append(tank.getPressure() + " ");
					}
					if (Height_ld_flag) {
						alarmRecord.setHeightLd(tank.getHeight_ld() - alarm.getHeight());
						stringBuilder.append(tank.getHeight_ld() + " ");
					}
					if (Height_sf1_flag) {
						alarmRecord.setHeightLd(tank.getHeight_sf1() - alarm.getHeight());
						stringBuilder.append(tank.getHeight_sf1() + " ");
					}
					if (Height_sf2_flag) {
						alarmRecord.setHeightLd(tank.getHeight_sf2() - alarm.getHeight());
						stringBuilder.append(tank.getHeight_sf2() + " ");
					}
					if (Temperature_flag) {
						stringBuilder.append(tank.getTemperature() + " ");
					}

                    EmailInfo emailInfo = new EmailInfo();
					emailInfo.setFrom(email);
					emailInfo.setTo(emailMap.get(alarm.getUsername()));
					emailInfo.setSubject(subject);
					emailInfo.setMsg(stringBuilder.toString());
					kafkaTemplate.send("emailTopic", JSON.toJSONString(emailInfo));

					alarmRecordService.insertAlarmRecord(alarmRecord);
				}
			}
		}
	}


}
