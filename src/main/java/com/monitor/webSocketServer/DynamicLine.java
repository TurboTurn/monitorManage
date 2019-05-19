package com.monitor.webSocketServer;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitor.pojo.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author : ys
 * @date : 2019/5/8 10:52 星期三
 **/

@Component
@ServerEndpoint("/dynamicLine")
public class DynamicLine {

	private Logger logger = LoggerFactory.getLogger(DynamicLine.class);

	public static CopyOnWriteArraySet<DynamicLine> dynamicLines = new CopyOnWriteArraySet<>();

	//与某个客户端的连接会话，需要通过它来给客户端发送数据
	private Session session;

	/**
	 * 连接建立成功调用的方法
	 *
	 * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException {//todo 第一次需要从数据库查询last()最新的数据，填充到仪表板，接下来只需要等待更新推送
		this.session = session;
		dynamicLines.add(this);     //加入set中
		logger.info("有新连接加入，当前连接数{}", dynamicLines.size());

		//todo 第一次查数据并返回
		long time = new Date().getTime();
		ArrayList<Point> list = new ArrayList<>();
		for (int i = -19; i <= 0; i++) {
			list.add(new Point(time + i * 2000, Math.random()));
		}
		HashMap<String,Object> map = new HashMap<>();
		map.put("status","open");
		map.put("data",list);
		String s = JSON.toJSONString(map);
		logger.info(s);
		sendMessage(s);
	}

	//	@Test
	public static void main(String[] args) throws JsonProcessingException {

//		ObjectMapper mapper = new ObjectMapper();
//		String json = mapper.writeValueAsString(list);
		HashMap<String,Object> map = new HashMap<>();
		long time = new Date().getTime();
		ArrayList<Point> list = new ArrayList<>();
		for (int i = -19; i <= 0; i++) {
			list.add(new Point(time + i * 2000, Math.random()));
		}
		String s = JSON.toJSONString(list);
		map.put("status","open");
		map.put("data",list);
		System.out.println(JSON.toJSONString(map));
	}


	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		dynamicLines.remove(this);  //从set中删除
		logger.info("socket连接关闭！当前连接数{}", dynamicLines.size());
	}

	/**
	 * 收到客户端消息后调用的方法
	 *
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {//todo 应该用不到
		System.out.println("来自客户端的消息:" + message);
		//群发消息
		for (DynamicLine item : dynamicLines) {
			try {
				item.sendMessage(message);
			} catch (IOException e) {
				e.printStackTrace();
				continue;
			}
		}
	}

	/**
	 * 发生错误时调用
	 *
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("socket发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) throws IOException {
		this.session.getBasicRemote().sendText(message);
		//this.session.getAsyncRemote().sendText(message);
	}
}
