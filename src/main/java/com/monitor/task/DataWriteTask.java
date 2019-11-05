package com.monitor.task;

import com.monitor.dao.PressureDao;
import com.monitor.measurement.Pressure;
import com.monitor.util.InfluxDBManager;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author : ys
 * @date : 2019/10/14 14:35 星期一
 **/

//@Component//注解掉任务不会启动
public class DataWriteTask {//模拟数据采集，写入influxDB
	Logger logger = LoggerFactory.getLogger(DataWriteTask.class);

	@Autowired
	PressureDao pressureDao;

	private String dbName = "monitorMS";
	private double pres = 0.2;//1MPa = 10^6Pa = 1000KPa  大气压10^5Pa = 0.1MPa
	private int count = 0;
	private boolean add = true;

	@Async(value = "pushThreadPool")
//	@Scheduled(cron = "0/2 * * * * ?")
	public void writePressure() { //压力变送器数据
		Pressure pressure = new Pressure("CG1", "YB-01", pres);
		pressure.setTime(Instant.now());
		pressureDao.save(pressure);
//		logger.info("{}写入influxdb数据{}",Thread.currentThread().getName(),pressure);
		pres += add ? 0.001 : -0.001;
		count++;
		if (count == 5) {//随机加或减的次数
			count = random.nextInt(5);
			add = !add;
		}
	}


	private double pressure = 0.2;
	private int batchCount = 0;
	private boolean batchAdd = true;
	private Random random = new Random();
	private InfluxDB influxDB = InfluxDBManager.getInfluxDB();
	@Async(value = "pushThreadPool")
	@Scheduled(cron = "0/2 * * * * ?")
	public void writePressureBatch() { //批量写入压力
//		influxDB.enableBatch(BatchOptions.DEFAULTS);//开启批处理
		logger.debug("注解component");
		String measurement = "pressure";
		BatchPoints batchPoints = BatchPoints
				.database(dbName)
//				.tag("async", "true")
				.consistency(InfluxDB.ConsistencyLevel.ALL)
				.build();

		long currentTime = System.currentTimeMillis();
		Point point1 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG1")
				.tag("equipNumber", "YB-01")
				.addField("pressure", pressure + random.nextInt(3) * 0.001)//保证每个设备的值不同
				.build();
		Point point2 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG2")
				.tag("equipNumber", "YB-02")
				.addField("pressure", pressure + random.nextInt(3) * 0.001)
				.build();
		Point point3 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG3")
				.tag("equipNumber", "YB-03")
				.addField("pressure", pressure + random.nextInt(3) * 0.001)
				.build();
		Point point4 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG4")
				.tag("equipNumber", "YB-04")
				.addField("pressure", pressure + random.nextInt(3) * 0.001)
				.build();
		Point point5 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG5")
				.tag("equipNumber", "YB-05")
				.addField("pressure", pressure + random.nextInt(3) * 0.001)
				.build();
		Point point6 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG6")
				.tag("equipNumber", "YB-06")
				.addField("pressure", pressure + random.nextInt(3) * 0.001)
				.build();

		batchPoints.point(point1);//生成批量数据
		batchPoints.point(point2);
		batchPoints.point(point3);
		batchPoints.point(point4);
		batchPoints.point(point5);
		batchPoints.point(point6);
		influxDB.write(batchPoints);//批量写入

		pressure += batchAdd ? 0.001 : -0.001;
		batchCount++;
		if (batchCount == 5) {//随机加或减的次数
			batchCount = random.nextInt(5);
			batchAdd = !batchAdd;
		}
	}

	private double height = 12;//液位计高度 30米上限
	private int heightCount = 0;
	private boolean heightAdd = true;
	@Async(value = "pushThreadPool")
	@Scheduled(cron = "0/2 * * * * ?")
	public void writeLDHeight() { //雷达液位计液位高度  伺服液位计液位高度
		String measurement = "height";
		BatchPoints batchPoints = BatchPoints
				.database(dbName)
				.consistency(InfluxDB.ConsistencyLevel.ALL)
				.build();
		long currentTime = System.currentTimeMillis();
		Point point1 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG1")
				.tag("equipNumber", "LD-01")
				.addField("height",height + random.nextInt(3) * 0.001)
				.build();
		Point point2 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG2")
				.tag("equipNumber", "LD-02")
				.addField("height",height + random.nextInt(3) * 0.001)
				.build();
		Point point3 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG3")
				.tag("equipNumber", "LD-03")
				.addField("height",height + random.nextInt(3) * 0.001)
				.build();
		Point point4 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG4")
				.tag("equipNumber", "LD-04")
				.addField("height",height + random.nextInt(3) * 0.001)
				.build();
		Point point5 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG5")
				.tag("equipNumber", "LD-05")
				.addField("height",height + random.nextInt(3) * 0.001)
				.build();
		Point point6 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("partition", "CG6")
				.tag("equipNumber", "LD-06")
				.addField("height",height + random.nextInt(3) * 0.001)
				.build();

		batchPoints.point(point1);
		batchPoints.point(point2);
		batchPoints.point(point3);
		batchPoints.point(point4);
		batchPoints.point(point5);
		batchPoints.point(point6);
		influxDB.write(batchPoints);

		height += heightAdd ? 0.001 : -0.001;
		heightCount ++;
		if(heightCount == 5){
			heightCount = random.nextInt(5);
			heightAdd = !heightAdd;
		}
	}

}
