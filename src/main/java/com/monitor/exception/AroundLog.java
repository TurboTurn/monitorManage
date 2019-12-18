package com.monitor.exception;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect//表示切面类，不加则此类无效
public class AroundLog {//使用aop捕获异常
	private static Logger logger = LoggerFactory.getLogger(AroundLog.class);

	@Pointcut("execution(* com.monitor.task..*.*(..))")
	public void taskPointCut() {//后台定时任务
	}

	@Pointcut("execution(* com.monitor.mq..*.*(..))")
	public void consumerPointCut() {//消费者线程
	}

	@Around(value = "taskPointCut()")
	public Object serviceLog(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		List<Object> asList = Arrays.asList(args);
		Signature signature = point.getSignature();
		String packageName = signature.getDeclaringTypeName();
		String name = signature.getName();
		Object result = null;
		try {
			//目标方法之前要执行的操作,相当于@before
			//logger.info("[定时任务日志开始] {}.{}() 参数为:{}", packageName, name, asList);
			//调用目标方法
			result = point.proceed(args);
		} catch (Throwable e) {
			//目标方法抛出异常信息之后的操作，相当于@AfterThrowing
			logger.warn("【定时任务日志异常】{}.{}()方法异常，异常信息为:{}", packageName, name, e.toString());
//			throw e;
		}
		return result;
	}

	@Around(value = "consumerPointCut()")
	public Object consumerLog(ProceedingJoinPoint point) throws Throwable {
		Object[] args = point.getArgs();
		Signature signature = point.getSignature();
		String packageName = signature.getDeclaringTypeName();
		String name = signature.getName();
		Object result = null;
		try {
			try {
				result = point.proceed(args);//调用目标方法
			} finally {
			}
		} catch (Exception e) {
			logger.warn("【消费者日志异常】{}.{}()方法异常，异常信息为:{}", packageName, name, e.toString());
//			throw e;
		}
		return result;
	}
}
