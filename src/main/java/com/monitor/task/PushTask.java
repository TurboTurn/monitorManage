package com.monitor.task;

import com.alibaba.fastjson.JSON;
import com.monitor.dao.PressureDao;
import com.monitor.measurement.Pressure;
import com.monitor.pojo.Point;
import com.monitor.webSocketServer.DynamicLine;
import com.monitor.webSocketServer.GaugeWebSocket;
import com.monitor.webSocketServer.LineWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static com.monitor.webSocketServer.DynamicLine.dynamicLines;
import static com.monitor.webSocketServer.GaugeWebSocket.gaugeWebSockets;
import static com.monitor.webSocketServer.LineWebSocket.lineWebSocketSet;

/**
 * @author : ys
 * @date : 2019/4/26 9:12 星期五
 **/
@Component
public class PushTask {
	private Logger logger = LoggerFactory.getLogger(PushTask.class);
	//多个Schedule只用一个线程schedule-1
	//基于注解@Scheduled默认为单线程，开启多个任务时，任务的执行时机会受上一个任务执行时间的影响
	//添加Async改为多线程

//	@Resource(name = "pushThreadPool")
/*	@Autowired
	TaskExecutor pushThreadPool;*/

	@Autowired
	PressureDao pressureDao;

	/**
	 * 仪表盘推送
	 */

//	@Async(value = "pushThreadPool")
//	@Scheduled(cron = "0/2 * * * * ?")
	public void gauge() {
		for (GaugeWebSocket webSocket : gaugeWebSockets) {
			Pressure pre = pressureDao.getLast();
			String s = String.format("%.3f", pre.getPressure());
			webSocket.sendMessage(s);
		}
	}//仪表盘结束



	/**
	 * 曲线图推送
	 */
	private static ArrayDeque<Integer> deque = new ArrayDeque<>();
	private static Random random = new Random();
	static {
		int b = 200;
		for (int i = 0; i < 60; i++) {
			deque.addLast(b + random.nextInt(100));
		}
	}

//	@Async(value = "pushThreadPool")
//	@Scheduled(cron = "0/2 * * * * ?")
	public void line() {
		for (LineWebSocket lineWebSocket : lineWebSocketSet) {
			lineWebSocket.sendMessage(deque.toString());//推送数据
//			logger.info("线程{}推送曲线数据{}",Thread.currentThread().getName(),lineWebSocket);
		}
		deque.removeFirst();
		deque.addLast(300 + random.nextInt(100));//更新数据
	}//曲线图结束

//	@Async(value = "pushThreadPool")
//	@Scheduled(cron = "0/2 * * * * ?")
	public void dynamicLine() {
		for (DynamicLine dynamicLine : dynamicLines){
			HashMap<String,Object> map = new HashMap<>();
			map.put("status","message");
			Point point = new Point(new Date().getTime(),Math.random());
			map.put("data",point);
			dynamicLine.sendMessage(JSON.toJSONString(map));
		}
	}

}
