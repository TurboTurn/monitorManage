package com.monitor.task;

import com.alibaba.fastjson.JSON;
import com.monitor.pojo.Tank;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

//@Component
public class HttpWriter {//数据采集程序，部署到库区，将数据上传
	private Logger logger = LoggerFactory.getLogger(HttpWriter.class);

	private float pressure = 0.2f;
	private int pressCount = 0;
	private boolean pressAdd = true;

	private float height = 12f;//液位计高度 30米上限
	private int heightCount = 0;
	private boolean heightAdd = true;

	private float temperature = 25f;//
	private int tempCount = 0;
	private boolean tempAdd = true;

	private Random random = new Random();

	@Async(value = "pushThreadPool")
	@Scheduled(cron = "0/5 * * * * ?")
	public void write() {//将数据发送到http接口
		long time = System.currentTimeMillis();
		ArrayList<Tank> list = new ArrayList<>();
		Tank t1 = new Tank(time, "tank1", "丙烷",
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				pressure + random.nextInt(3) * 0.001f,
				temperature + random.nextInt(3) * 0.1f, 1, 1);
		Tank t2 = new Tank(time, "tank2", "丙烷",
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				pressure + random.nextInt(3) * 0.001f,
				temperature + random.nextInt(3) * 0.1f, 1, 1);
		Tank t3 = new Tank(time, "tank3", "丙烷",
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				pressure + random.nextInt(3) * 0.001f,
				temperature + random.nextInt(3) * 0.1f, 1, 1);
		Tank t4 = new Tank(time, "tank4", "丁烷",
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				pressure + random.nextInt(3) * 0.001f,
				temperature + random.nextInt(3) * 0.1f, 1, 1);
		Tank t5 = new Tank(time, "tank5", "丁烷",
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				pressure + random.nextInt(3) * 0.001f,
				temperature + random.nextInt(3) * 0.1f, 1, 1);
		Tank t6 = new Tank(time, "tank6", "丁烷",
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				height + random.nextInt(3) * 0.001f,
				pressure + random.nextInt(3) * 0.001f,
				temperature + random.nextInt(3) * 0.1f, 1, 1);
		list.add(t1);
		list.add(t2);
		list.add(t3);
		list.add(t4);
		list.add(t5);
		list.add(t6);
		//http发送list
//		String url = "http://127.0.0.1:8080/mq/produce";
		String url = "http://127.0.0.1:8080/mq/collect/tank";
		String jsonString = JSON.toJSONString(list);
//		System.out.println(jsonString);
		postData(url, jsonString);//上传数据

		pressure += pressAdd ? 0.001f : -0.001f;
		pressCount++;
		if (pressCount == 5) {//随机加或减的次数
			pressCount = random.nextInt(5);
			pressAdd = !pressAdd;
		}

		height += heightAdd ? 0.001f : -0.001f;
		heightCount++;
		if (heightCount == 5) {
			heightCount = random.nextInt(5);
			heightAdd = !heightAdd;
		}

		temperature += tempAdd ? 0.1f : -0.1f;
		tempCount++;
		if (tempCount == 5) {
			tempCount = random.nextInt(5);
			tempAdd = !tempAdd;
		}
	}

	public void postData(String url, String jsonString) {//同步
		OkHttpClient client = new OkHttpClient();
		RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
		final Request request = new Request.Builder()
				.url(url)
				.post(body).build();
		Call call = client.newCall(request);
		try {
			Response response = call.execute();
			if (response.isSuccessful()) {
				response.close();
			} else {
				logger.warn("http请求不成功");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void postData2(String url, String jsonString) {//异步，使用了线程池
		OkHttpClient client = new OkHttpClient();
		RequestBody body = FormBody.create(MediaType.parse("application/json; charset=utf-8"), jsonString);
		final Request request = new Request.Builder()
				.url(url)
				.post(body).build();
		Call call = client.newCall(request);
		call.enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				logger.warn("http请求失败，{}", e.getMessage());
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if (response.isSuccessful()) {
					response.close();
					//A connection to http://localhost:8080/ was leaked. Did you forget to close a response body? 出现此WARN
				} else {
					logger.warn("http请求不成功");
				}
			}
		});
	}
}
