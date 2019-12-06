package com.monitor.webSocketServer;

import com.monitor.dao.PressureDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/detailWebsocket")//罐区详细监控的连接
public class DetailWebSocket {// 仪表盘数据推送，该类实例为多例，每个socket连接都产生一个实例

	private Logger logger = LoggerFactory.getLogger(DetailWebSocket.class);

	public static ConcurrentHashMap<String,CopyOnWriteArraySet<DetailWebSocket>> detailWebSockets = new ConcurrentHashMap<>();

	private String tankId;
	private Session session;//与某个客户端的连接会话，需要通过它来给客户端发送数据

	final PressureDao pressureDao = new PressureDao();


	@OnOpen
	public void onOpen(Session session) {
		sendMessage(String.format("%.3f", pressureDao.getLast().getPressure()));//todo 第一次查数据并返回
	}


	@OnClose
	public void onClose(){
		detailWebSockets.get(tankId).remove(this);  //从set中删除
		logger.info("socket连接关闭！当前油罐{}连接数{}", tankId,detailWebSockets.get(tankId).size());
	}

	@OnMessage
	public void onMessage(String message, Session session) {//message表示油罐id,分油罐保存会话信息
		this.tankId = message;
		this.session = session;
		CopyOnWriteArraySet<DetailWebSocket> set = detailWebSockets.getOrDefault(tankId, new CopyOnWriteArraySet<>());
		set.add(this);
		detailWebSockets.put(tankId, set);
	}


	@OnError
	public void onError(Session session, Throwable error){
		logger.error("webSocket发生错误:{}", error.toString());
		error.printStackTrace();//不打印错误堆栈，在微信打开网页然后关闭会报错
	}


	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

