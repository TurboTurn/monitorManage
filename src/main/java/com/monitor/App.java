package com.monitor;

import com.monitor.controller.WebSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static com.monitor.controller.WebSocketServer.webSocketSet;

/**
 * @author : ys
 * @date : 2019/4/15 15:15 星期一
 * App不能跨包，如果在App所在包外面的包定义Controller是不会绑定映射的，浏览器访问不到
 * RestController等价于@Controller和@ResponseBody
 **/
@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class,args);

		new Thread(()->{// todo 模拟推送数据线程，后期用opc收集数据，与上一次有变化时才推送
			double a=12.1;
			while (true){
				for (WebSocketServer webSocket:webSocketSet){
					try {
						String s = String.format("%.2f",a);
						webSocket.sendMessage(s);
						System.out.println("推送给"+webSocket+":"+s);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println();
				try {
					TimeUnit.SECONDS.sleep(2);
					a+=1.32;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
