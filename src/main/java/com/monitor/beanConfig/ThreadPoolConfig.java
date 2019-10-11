package com.monitor.beanConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : ys
 * @date : 2019/10/11 18:04 星期五
 **/

@Configuration
@EnableScheduling    //该注释必须至少出现一次，且出现一次即可，才会Initializing ExecutorService 'taskScheduler'，任务才会执行
@EnableAsync    //同上，此外，不开启的话，所有任务共用scheduling-1单个线程去运行任务，会导致其他任务受影响
public class ThreadPoolConfig {

	@Bean
	public TaskExecutor pushThreadPool() {// 推送webSocket数据线程池
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);// 设置核心线程数
		executor.setMaxPoolSize(20);// 设置最大线程数
		executor.setQueueCapacity(20);// 设置队列容量
		executor.setKeepAliveSeconds(60);// 设置最大线程池线程活跃时间（秒）
		executor.setThreadNamePrefix("push-");
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());// 在原来的调用线程中执行任务
		executor.setWaitForTasksToCompleteOnShutdown(true);// 等待所有任务结束后再关闭线程池,默认false
		executor.initialize();// 初始化加载
		return executor;
	}

}
