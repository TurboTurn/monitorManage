package com.monitor.ActiveMQ;


import com.monitor.pojo.Tank;
import com.monitor.util.InfluxDBManager;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBIOException;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.concurrent.TimeUnit;


/**
 * @author : ys
 * @date : 2019/4/15 16:21 星期一
 **/


@Component
public class MQConsumer {
	private Logger logger = LoggerFactory.getLogger(MQConsumer.class);

	private InfluxDB influxDB = InfluxDBManager.getInfluxDB();

	//	接收消息的方法
	@JmsListener(destination = "tank")
	public void readMessage(ObjectMessage message){//消费者将球罐数据存储到influxDB
		Tank[] list = new Tank[0];
		try {
			list = (Tank[]) message.getObject();
		} catch (JMSException e) {
			logger.warn("从mq取消息失败：{}",e.toString());
		}

		String dbName = "monitorMS";
		String measurement = "factory1";
		BatchPoints batchPoints = BatchPoints
				.database(dbName)
				.consistency(InfluxDB.ConsistencyLevel.ALL)
				.build();
		for (Tank tank : list) {
			Point point = Point
					.measurement(measurement)
					.time(tank.getTime(), TimeUnit.MILLISECONDS)
					.tag("a1_tank", tank.getA1_tank())//球罐名,tag加前缀来控制tag在tag和field的顺序
					.tag("a2_oil", tank.getA2_oil())//油品名称
					.addField("valve", tank.isValve())//阀门状态
					.addField("height_sf1", tank.getHeight_sf1())//伺服液位计1，使用随机值造成每个设备的差别
					.addField("height_sf2", tank.getHeight_sf2())//伺服液位计2
					.addField("height_ld", tank.getHeight_ld())//雷达液位计
					.addField("pressure", tank.getPressure())//压力传感器
					.addField("temperature", tank.getTemperature())//温度
					.build();
			batchPoints.point(point);
		}
		try {
			if(list.length > 0)
				influxDB.write(batchPoints);//批量写入
		}catch (InfluxDBIOException e){
			logger.warn("InfluxDB连接超时,{}",e.toString());
		}
	}

//	@JmsListener(destination = "tank")
//	public void readMessage1(ObjectMessage message) throws JMSException {//自定义报警信息
//		logger.info("消费者二：收到消息：{}",(User)message.getObject());
//	}
}
