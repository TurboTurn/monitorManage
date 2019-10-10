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
		System.out.println("系统初始化完成");
	}


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
