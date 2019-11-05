package com.monitor.webSocketServer;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServerEndpoint 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
 * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
 */
@Component//不加会导致websocket握手失败
@ServerEndpoint("/websocket")
public class ChatWebSocket {
	//静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
	private static volatile int onlineCount = 0;

	//用来存放每个客户端对应的WebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
	private static ConcurrentHashMap<ChatWebSocket,Integer> chatWebSockets = new ConcurrentHashMap<>();

	private Session session;//与某个客户端的连接会话，需要通过它来给客户端发送数据

	/**
	 * 连接建立成功调用的方法
	 * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
	 */
	@OnOpen
	public void onOpen(Session session){
		this.session = session;
		chatWebSockets.put(this,0);     //加入set中
//		addOnlineCount();           //在线数加1
		System.out.println("有新连接加入！当前在线人数为" + chatWebSockets.size());
		broadcast("有新连接加入！当前在线人数为" + chatWebSockets.size());
	}

	@OnClose
	public void onClose(){
		chatWebSockets.remove(this);  //从set中删除
//		subOnlineCount();           //在线数减1
		System.out.println("有一连接关闭！当前在线人数为" + chatWebSockets.size());
		broadcast("有人退出群聊！当前在线人数为" + chatWebSockets.size());
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("来自客户端的消息:" + message);
		//群发消息
		broadcast(message);
	}

	@OnError
	public void onError(Session session, Throwable error){
		System.out.println("发生错误");
		error.printStackTrace();
	}

	//发送消息
	public void sendMessage(String message) {
		try {
			this.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//群发消息
	public void broadcast(String message){
		for (ChatWebSocket webSocket : chatWebSockets.keySet()){
			webSocket.sendMessage(message);
		}
	}
}
