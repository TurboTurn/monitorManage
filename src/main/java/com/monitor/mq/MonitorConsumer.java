package com.monitor.mq;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.monitor.pojo.Tank;
import com.monitor.webSocketServer.GlobalWebSocket;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.monitor.webSocketServer.GlobalWebSocket.globalWebSockets;

@Component
public class MonitorConsumer {//库区总体监控
	Map<String, Tank> map = new HashMap<>();//上次采集数据缓存
	int count = 0;
	@KafkaListener(topics = "tankTopic", containerFactory = "containerFactory2")
	public void onMessage(String message) {
		List<Tank> list = JSONObject.parseArray(message, Tank.class);
		count++;
		System.out.println("总体监控模块收到数据"+count);
		for (Tank tank : list) {//遍历每个油罐数据
			Tank tankTemp = map.getOrDefault(tank.getA1_tank(), null);
			if (!tank.equals(tankTemp)) {//与缓存不同
				map.put(tank.getA1_tank(), tank);//更新缓存
				//推送数据到前端
				for (GlobalWebSocket webSocket : globalWebSockets) {
					String msg = JSON.toJSONString(tank);
					webSocket.sendMessage(msg);
				}
			}
		}
	}
}
