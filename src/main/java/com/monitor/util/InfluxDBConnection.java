package com.monitor.util;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.*;
import org.influxdb.dto.Point.Builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * InfluxDB数据库连接操作类
 *
 */
public class InfluxDBConnection {

	public static void main(String[] args) {
		InfluxDBConnection influxDBConnection = new InfluxDBConnection("admin", "admin", "http://127.0.0.1:8086", "ys", null);
		Map<String, String> tags = new HashMap<>();
		tags.put("tag", "good");
		Map<String, Object> fields = new HashMap<>();
		fields.put("field", 1544f);
		// 数值型，InfluxDB的字段类型，由第一天插入的值得类型决定
		influxDBConnection.insert("table1", tags, fields, System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	private final String username;// 用户名
	private final String password;// 密码
	private final String openurl;// 连接地址
	private String database;// 数据库
	private String retentionPolicy;// 保留策略

	private InfluxDB influxDB;

	public InfluxDBConnection(String username, String password, String openurl, String database, String retentionPolicy) {
		this.username = username;
		this.password = password;
		this.openurl = openurl;
		this.database = database;
		this.retentionPolicy = retentionPolicy == null || retentionPolicy.equals("") ? "autogen" : retentionPolicy;
		influxDbBuild();
	}
	/**
	 * 连接时序数据库 ，若不存在则创建
	 */
	public InfluxDB influxDbBuild() {
		if (influxDB == null) {
			influxDB = InfluxDBFactory.connect(openurl, username, password);
		}
		influxDB.setRetentionPolicy(retentionPolicy);
		influxDB.setLogLevel(InfluxDB.LogLevel.NONE);
		return influxDB;
	}

	/**
	 * 创建数据库
	 */
	public void createDB(String dbName) {
		influxDB.createDatabase(dbName);
	}
	/**
	 * 关闭数据库
	 */
	public void close() {
		influxDB.close();
	}

	/**
	 * 删除数据库
	 */
	public void deleteDB(String dbName) {
		influxDB.deleteDatabase(dbName);
	}

	/**
	 * 测试连接是否正常
	 */
	public boolean ping() {
		Pong pong;
		pong = influxDB.ping();
		return pong != null;
	}



	/**
	 * 创建自定义保留策略
	 *
	 * @param policyName 策略名
	 * @param duration 保存天数
	 * @param replication 保存副本数量
	 * @param isDefault 是否设为默认保留策略
	 *
	 */
	public void createRetentionPolicy(String policyName, String duration, int replication, Boolean isDefault) {
		String sql = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s ", policyName,
				database, duration, replication);
		if (isDefault) {
			sql = sql + " DEFAULT";
		}
		this.query(sql);
	}

	/**
	 * 创建默认的保留策略
	 *
	 * 	策略名：default，保存天数：30天，保存副本数量：1
	 *            设为默认保留策略
	 */
	public void createDefaultRetentionPolicy() {
		String command = String.format("CREATE RETENTION POLICY \"%s\" ON \"%s\" DURATION %s REPLICATION %s DEFAULT",
				"default", database, "30d", 1);
		this.query(command);
	}

	/**
	 * 查询
	 * @param command 查询语句
	 */
	public QueryResult query(String command) {
		return influxDB.query(new Query(command, database));
	}

	/**
	 * 插入
	 * @param measurement 表
	 * @param tags 标签
	 * @param fields 字段
	 */
	public void insert(String measurement, Map<String, String> tags, Map<String, Object> fields, long time, TimeUnit timeUnit) {
		Builder builder = Point.measurement(measurement);
		builder.tag(tags);
		builder.fields(fields);
		/*if (0 != time) {
			builder.time(time, timeUnit);
		}*/
		influxDB.write(database, retentionPolicy, builder.build());
	}

	/**
	 * 批量写入测点
	 *
	 * @param batchPoints
	 */
	public void batchInsert(BatchPoints batchPoints) {
		influxDB.write(batchPoints);
		// influxDB.enableGzip();
		// influxDB.enableBatch(2000,100,TimeUnit.MILLISECONDS);
		// influxDB.disableGzip();
		// influxDB.disableBatch();
	}

	/**
	 * 批量写入数据
	 *
	 * @param database 数据库
	 * @param retentionPolicy 保存策略
	 * @param consistency 一致性
	 * @param records 要保存的数据（调用BatchPoints.lineProtocol()可得到一条record）
	 */
	public void batchInsert(final String database, final String retentionPolicy, final ConsistencyLevel consistency,
							final List<String> records) {
		influxDB.write(database, retentionPolicy, consistency, records);
	}

	/**
	 * 删除
	 *
	 * @param command  删除语句
	 * @return 返回错误信息
	 */
	public String deleteMeasurementData(String command) {
		QueryResult result = influxDB.query(new Query(command, database));
		return result.getError();
	}



	/**
	 * 构建Point
	 *
	 * @param measurement
	 * @param time
	 * @param fields
	 * @return
	 */
	public Point pointBuilder(String measurement, long time, Map<String, String> tags, Map<String, Object> fields) {
		Point point = Point.measurement(measurement).time(time, TimeUnit.MILLISECONDS).tag(tags).fields(fields).build();
		return point;
	}

}


