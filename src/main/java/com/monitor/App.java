package com.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * @author : ys
 * @date : 2019/4/15 15:15 星期一
 * App不能跨包，如果在App所在包外面的包定义Controller是不会绑定映射的，浏览器访问不到
 * RestController等价于@Controller和@ResponseBody
 **/
@SpringBootApplication
@ComponentScan(basePackages = "com.monitor.*")//不指明单元测试会报错
public class App {

	@PostConstruct
	public void init() {
		System.out.println("系统启动完成");
	}


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

		//仪表板
		/*new Thread(() -> {// todo 模拟推送数据线程，后期用opc收集数据，与上一次有变化时才推送
			double a = 12.1;
			boolean flag = true;
			while (true) {
				for (GaugeWebSocket webSocket : gaugeWebSockets) {
					try {
						String s = String.format("%.2f", a);
						webSocket.sendMessage(s);
//						System.out.println("推送给" + webSocket + ":" + s);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				try {
					TimeUnit.SECONDS.sleep(2);
					if (flag) {
						a += 1.32;
						flag = false;
					} else {
						a -= 1.31;
						flag = true;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		},"仪表板推送").start();*/
		/*曲线图*/
		/*new Thread(() -> {
			ArrayDeque<Integer> deque = new ArrayDeque<>();
			int b = 200;
			Random random = new Random();
			for (int i = 0; i < 60; i++) {
				deque.addLast(b + random.nextInt(100));
			}
			while (true) {
				for (LineWebSocket lineWebSocket : lineWebSocketSet) {
					try {
						lineWebSocket.sendMessage(deque.toString());//推送数据
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				deque.removeFirst();
				deque.addLast(300 + new Random().nextInt(100));//更新数据
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		},"曲线图推送").start();*/
	}
}
