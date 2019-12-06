package com.monitor.util;

import com.alibaba.fastjson.JSON;
import com.monitor.measurement.Table;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/19 18:46 星期日
 **/
public class InfluxDBManager {
	private InfluxDBManager(){}
	private static String url = "http://stephenyi.cn:8086";
	private static String dbName = "monitorMS";
	private static String username = "root";
	private static String password = "admin";
	/**
	 * 这种方式跟饿汉式方式采用的机制类似，但又有不同。
	 * 两者都是采用了类装载的机制来保证初始化实例时只有一个线程。
	 * 不同的地方在饿汉式方式是只要Singleton类被装载就会实例化，没有Lazy-Loading的作用，
	 * 而静态内部类方式在Singleton类被装载时并不会立即实例化，而是在需要实例化时，
	 * 调用getInstance方法，才会装载SingletonInstance类，从而完成Singleton的实例化。
	 * 类的静态属性只会在第一次加载类的时候初始化，
	 * 所以在这里，JVM帮助我们保证了线程的安全性，在类进行初始化时，别的线程是无法进入的。
	 * 优点：避免了线程不安全，延迟加载，效率高。
	 */
	private static class InfluxInstance{//单例influxDB
		private static final InfluxDB influxDB = InfluxDBFactory.connect(url, username, password);
	}

	private static class MapperInstance{//单例mapper
		private static final InfluxDBMapper mapper = new InfluxDBMapper(getInfluxDB());
	}

	public static InfluxDB getInfluxDB() {
		return InfluxInstance.influxDB;
	}

	public static InfluxDBMapper getInfluxDBMapper(){
		return MapperInstance.mapper;
	}


	public static void main(String[] args) {
//		delete();
//		add();
		String sql = String.format("select * from factory1 where a1_tank = '%s' and time > now()-30m","tank1");
		List<Table> list = InfluxDBManager.getInfluxDBMapper().query(new Query(sql,"monitorMS"), Table.class);
		System.out.println(list);
//		System.out.println(JSON.toJSONString(list));
	}

	/**
	 * 创建自定义保留策略
	 * @param policyName 策略名
	 * @param duration 保存天数
	 * @param replication 保存副本数量
	 * @param isDefault 是否设为默认保留策略
	 */
	public static void createRetentionPolicy(String policyName, String duration, int replication, Boolean isDefault) {
		String sql = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s ",
				policyName, dbName, duration, replication);
		if (isDefault) {
			sql = sql + " DEFAULT";
		}
		QueryResult result = getInfluxDB().query(new Query(sql));
		System.out.println(result);
	}


	public static void add(){
//		创建默认的保留策略，策略名：default，保存天数：30天，保存副本数量：1 设为默认保留策略
		createRetentionPolicy("default","7d",1,true);
		createRetentionPolicy("forever","0s",1,false);

		//创建连续查询 取每分钟平均值
		String sql = "create continuous query cq_mean_1m on monitorMS" +
				" BEGIN select mean(height_ld) as height_ld,mean(pressure) as pressure,mean(temperature) as temperature" +
				" into forever.factory1_1m from factory1 group by time(1m),a1_tank,a2_oil END";
		QueryResult result = getInfluxDB().query(new Query(sql));
		System.out.println(result);
	}

	public static void delete(){
		//删除连续查询
		String sql = "drop continuous query cq_mean_1m on monitorMS";
		QueryResult result = getInfluxDB().query(new Query(sql));
		System.out.println(result);

		//删除保留策略
		sql = "drop retention policy forever on monitorMS";
		result = getInfluxDB().query(new Query(sql));
		System.out.println(result);
	}

}
