package com.monitor.task;

import com.monitor.dao.PressureDao;
import com.monitor.measurement.Pressure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

/**
 * @author : ys
 * @date : 2019/10/14 14:35 星期一
 **/

@Component
public class DataWriteTask {
	Logger logger = LoggerFactory.getLogger(DataWriteTask.class);

	@Autowired
	PressureDao pressureDao;

	private double pres = 8.0;
	private int count = 0;
	private boolean add = true;

	@Async(value = "pushThreadPool")
	@Scheduled(cron = "0/2 * * * * ?")
	public void writePressure(){
		Pressure pressure = new Pressure("CG1", "YB-01", pres);
		pressure.setTime(Instant.now());
		pressureDao.save(pressure);
//		logger.info("{}写入influxdb数据{}",Thread.currentThread().getName(),pressure);
		pres += add ? 0.01 : -0.01;
		count ++;
		if(count == 5){//随机加或减的次数
			Random random = new Random();
			count = random.nextInt(5);
			add = !add;
		}
	}

	public static void main(String[] args) {
		Random random = new Random();
		for (int i=0;i<30;i++)
			System.out.print(random.nextInt(5) + " ");
	}
}
