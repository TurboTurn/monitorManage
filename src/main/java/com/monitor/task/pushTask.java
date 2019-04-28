package com.monitor.task;

import com.monitor.webSocketServer.GaugeWebSocket;
import com.monitor.webSocketServer.LineWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Random;

import static com.monitor.webSocketServer.GaugeWebSocket.gaugeWebSockets;
import static com.monitor.webSocketServer.LineWebSocket.lineWebSocketSet;

/**
 * @author : ys
 * @date : 2019/4/26 9:12 星期五
 **/
@Component
//@EnableScheduling
@EnableAsync
public class pushTask {
	private Logger logger = LoggerFactory.getLogger(pushTask.class);
	private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	//多个Schedule只用一个线程schedule-1
	//基于注解@Scheduled默认为单线程，开启多个任务时，任务的执行时机会受上一个任务执行时间的影响
	//添加Async改为多线程

	@Async
//	@Scheduled(fixedRate = 5000)
	public void reportCurrent() {
		logger.info("{},当前时间{}", Thread.currentThread().getName(), dateFormat.format(new Date()));
	}

	@Async
//	@Scheduled(cron = "0/5 * * * * ?")
	public void printTime() {
		logger.info("{} {}", Thread.currentThread().getName(), LocalDateTime.now().toString());
	}

	/**
	 * 仪表板推送
	 */
	private double a = 12.1;
	private boolean flag = true;

	@Async
	@Scheduled(cron = "0/2 * * * * ?")
	public void gauge() throws IOException {
		for (GaugeWebSocket webSocket : gaugeWebSockets) {
			String s = String.format("%.2f", a);
			webSocket.sendMessage(s);
//			logger.info("线程{}推送仪表板数据{}",Thread.currentThread().getName(),webSocket);
		}
		a += flag ? 1.32 : -1.32;
		flag = ! flag;
	}//仪表板结束

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
	@Async
	@Scheduled(cron = "0/2 * * * * ?")
	public void line() throws IOException {
		for (LineWebSocket lineWebSocket : lineWebSocketSet) {
			lineWebSocket.sendMessage(deque.toString());//推送数据
//			logger.info("线程{}推送曲线数据{}",Thread.currentThread().getName(),lineWebSocket);
		}
		deque.removeFirst();
		deque.addLast(300 + random.nextInt(100));//更新数据
	}

}
