package com.monitor.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author : ys
 * @date : 2019/4/26 9:12 星期五
 **/
//@Component
public class ScheduledTask {
	private Logger logger = LoggerFactory.getLogger(ScheduledTask.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//多个Schedule只用一个线程schedule-1
	//基于注解@Scheduled默认为单线程，开启多个任务时，任务的执行时机会受上一个任务执行时间的影响
	//添加Async改为多线程

	@Async(value = "pushThreadPool")//不设置value值时默认每次开启线程SimpleAsyncTaskExecutor-175
//	@Scheduled(fixedRate = 5000)	//注释该注解表示关闭任务
	@Scheduled(cron = "0/5 * * * * ?")
	public void reportCurrent(){
		logger.info("{},当前时间{}",Thread.currentThread().getName(),dateFormat.format(new Date()));
	}

}
