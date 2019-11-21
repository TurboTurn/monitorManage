package com.monitor.task;

import com.monitor.util.InfluxDBManager;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//@Component
public class DataWriteTask2 {//使用此类来写入数据，废弃，使用http方式
	Logger logger = LoggerFactory.getLogger(DataWriteTask2.class);

	private String dbName = "monitorMS";

	private double pressure = 0.2;
	private int pressCount = 0;
	private boolean pressAdd = true;

	private double height = 12;//液位计高度 30米上限
	private int heightCount = 0;
	private boolean heightAdd = true;

	private double temperature = 25;//
	private int tempCount = 0;
	private boolean tempAdd = true;

	private Random random = new Random();
	private InfluxDB influxDB = InfluxDBManager.getInfluxDB();

//	@Async(value = "pushThreadPool")
//	@Scheduled(cron = "0/5 * * * * ?")
	public void write1(){
		String measurement = "factory1";
		BatchPoints batchPoints = BatchPoints
				.database(dbName)
				.consistency(InfluxDB.ConsistencyLevel.ALL)
				.build();

		long currentTime = System.currentTimeMillis();
		Point point1 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("a1_tank", "tank1")//球罐名,tag加前缀来控制tag在tag和field的顺序
				.tag("a2_oil","丙烷")//油品名称
				.addField("valve",true)//阀门状态
				.addField("height_sf1",height + random.nextInt(3) * 0.001)//伺服液位计1，使用随机值造成每个设备的差别
				.addField("height_sf2",height + random.nextInt(3) * 0.001)//伺服液位计2
				.addField("height_ld",height + random.nextInt(3) * 0.001)//雷达液位计
				.addField("pressure", pressure + random.nextInt(3) * 0.001)//压力传感器
				.addField("temperature",temperature + random.nextInt(3) * 0.1)//温度
				.build();
		Point point2 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("a1_tank", "tank2")//球罐名
				.tag("a2_oil","丙烷")//油品名称
				.addField("valve",true)//阀门状态
				.addField("height_sf1",height + random.nextInt(3) * 0.001)//伺服液位计1，使用随机值造成每个设备的差别
				.addField("height_sf2",height + random.nextInt(3) * 0.001)//伺服液位计2
				.addField("height_ld",height + random.nextInt(3) * 0.001)//雷达液位计
				.addField("pressure", pressure + random.nextInt(3) * 0.001)//压力传感器
				.addField("temperature",temperature + random.nextInt(3) * 0.1)//温度
				.build();
		Point point3 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("a1_tank", "tank3")//球罐名
				.tag("a2_oil","丙烷")//油品名称
				.addField("valve",true)//阀门状态
				.addField("height_sf1",height + random.nextInt(3) * 0.001)//伺服液位计1，使用随机值造成每个设备的差别
				.addField("height_sf2",height + random.nextInt(3) * 0.001)//伺服液位计
				.addField("height_ld",height + random.nextInt(3) * 0.001)//雷达液位计
				.addField("pressure", pressure + random.nextInt(3) * 0.001)//压力传感器
				.addField("temperature",temperature + random.nextInt(3) * 0.1)//温度
				.build();
		Point point4 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("a1_tank", "tank4")//球罐名
				.tag("a2_oil","丁烷")//油品名称
				.addField("valve",true)//阀门状态
				.addField("height_sf1",height + random.nextInt(3) * 0.001)//伺服液位计1，使用随机值造成每个设备的差别
				.addField("height_sf2",height + random.nextInt(3) * 0.001)//伺服液位计
				.addField("height_ld",height + random.nextInt(3) * 0.001)//雷达液位计
				.addField("pressure", pressure + random.nextInt(3) * 0.001)//压力传感器
				.addField("temperature",temperature + random.nextInt(3) * 0.1)//温度
				.build();
		Point point5 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("a1_tank", "tank5")//球罐名
				.tag("a2_oil","丁烷")//油品名称
				.addField("valve",true)//阀门状态
				.addField("height_sf1",height + random.nextInt(3) * 0.001)//伺服液位计1，使用随机值造成每个设备的差别
				.addField("height_sf2",height + random.nextInt(3) * 0.001)//伺服液位计
				.addField("height_ld",height + random.nextInt(3) * 0.001)//雷达液位计
				.addField("pressure", pressure + random.nextInt(3) * 0.001)//压力传感器
				.addField("temperature",temperature + random.nextInt(3) * 0.1)//温度
				.build();
		Point point6 = Point
				.measurement(measurement)
				.time(currentTime, TimeUnit.MILLISECONDS)
				.tag("a1_tank", "tank6")//球罐名
				.tag("a2_oil","丁烷")//油品名称
				.addField("valve",true)//阀门状态
				.addField("height_sf1",height + random.nextInt(3) * 0.001)//伺服液位计1，使用随机值造成每个设备的差别
				.addField("height_sf2",height + random.nextInt(3) * 0.001)//伺服液位计
				.addField("height_ld",height + random.nextInt(3) * 0.001)//雷达液位计
				.addField("pressure", pressure + random.nextInt(3) * 0.001)//压力传感器
				.addField("temperature",temperature + random.nextInt(3) * 0.1)//温度
				.build();

		batchPoints.point(point1);//生成批量数据
		batchPoints.point(point2);
		batchPoints.point(point3);
		batchPoints.point(point4);
		batchPoints.point(point5);
		batchPoints.point(point6);
		influxDB.write(batchPoints);//批量写入

		pressure += pressAdd ? 0.001 : -0.001;
		pressCount++;
		if (pressCount == 5) {//随机加或减的次数
			pressCount = random.nextInt(5);
			pressAdd = !pressAdd;
		}

		height += heightAdd ? 0.001 : -0.001;
		heightCount ++;
		if(heightCount == 5){
			heightCount = random.nextInt(5);
			heightAdd = !heightAdd;
		}

		temperature += tempAdd ? 0.1 : -0.1;
		tempCount ++;
		if(tempCount == 5){
			tempCount = random.nextInt(5);
			tempAdd = !tempAdd;
		}
	}
}
