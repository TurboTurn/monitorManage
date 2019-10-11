package com.monitor.webSocketServer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author : ys
 * @date : 2019/4/23 18:52 星期二
 **/
@Component
@ServerEndpoint("/lineWebsocket")
public class LineWebSocket {
	private Logger logger = LoggerFactory.getLogger(LineWebSocket.class);
	//concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
	// 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	public static CopyOnWriteArraySet<LineWebSocket> lineWebSocketSet = new CopyOnWriteArraySet<>();

	private Session session;//与某个客户端的连接会话，需要通过它来给客户端发送数据


	/**
	 * 连接建立成功调用的方法
	 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session) throws IOException {//todo 第一次需要从数据库查询last()最新的数据，填充到仪表板，接下来只需要等待更新推送
		this.session = session;
		lineWebSocketSet.add(this);     //加入set中
		logger.info("有新连接加入，当前连接数{}",lineWebSocketSet.size());

		//todo 第一次查数据并返回
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		int b=200;
		for(int i=0;i<60;i++){
			deque.addLast(b);
		}
		sendMessage(deque.toString());
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(){
		lineWebSocketSet.remove(this);  //从set中删除
		logger.info("socket连接关闭！当前连接数{}",lineWebSocketSet.size());
	}

	/**
	 * 收到客户端消息后调用的方法
	 * @param message 客户端发送过来的消息
	 * @param session 可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {//todo 应该用不到
	}

	/**
	 * 发生错误时调用
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("发生错误");
		error.printStackTrace();
	}

	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
			//this.session.getAsyncRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
