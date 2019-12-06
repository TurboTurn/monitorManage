package com.monitor.task;

import com.alibaba.fastjson.JSON;
import com.monitor.pojo.Tank;
import com.monitor.util.InfluxDBManager;
import com.monitor.webSocketServer.DetailWebSocket;
import org.influxdb.dto.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;


import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import static com.monitor.webSocketServer.DetailWebSocket.detailWebSockets;

//@Component
public class DetailMonitor {//罐区详细监控

	@Async(value = "pushThreadPool")
	@Scheduled(cron = "0/5 * * * * ?")
	public void detailMonitor() {
		for (Map.Entry<String, CopyOnWriteArraySet<DetailWebSocket>> entry : detailWebSockets.entrySet()) {
			String tankId = entry.getKey();
			CopyOnWriteArraySet<DetailWebSocket> set = entry.getValue();
			if(set.size() > 0){//该油罐存在浏览器连接
				String sql = String.format("select * from factory1 where a1_tank = '%s' and time > now()-30m",tankId);
				List<Tank> list = InfluxDBManager.getInfluxDBMapper().query(new Query(sql), Tank.class);
				for (DetailWebSocket webSocket : set){
					webSocket.sendMessage(JSON.toJSONString(list));
				}
			}
		}
	}
}
