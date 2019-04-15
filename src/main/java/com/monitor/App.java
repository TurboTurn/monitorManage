package com.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
	}
}
