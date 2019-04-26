package com.monitor.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : ys
 * @date : 2019/4/26 9:12 星期五
 **/
@Component
@EnableScheduling
@EnableAsync
public class ScheduledTask {
	Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	private int a=3;
	//多个Schedule只用一个线程schedule-1
	//基于注解@Scheduled默认为单线程，开启多个任务时，任务的执行时机会受上一个任务执行时间的影响
	//添加Async改为多线程

	@Async
//	@Scheduled(fixedRate = 5000)
	public void reportCurrent(){
		logger.info("{},当前时间{}",Thread.currentThread().getName(),dateFormat.format(new Date()));
//		System.out.println(a);
		a+=2;
	}

	@Async
//	@Scheduled(cron = "0/5 * * * * ?")
	public void printTime() {
		logger.info("{} {}",Thread.currentThread().getName(),LocalDateTime.now().toString());
	}
}
