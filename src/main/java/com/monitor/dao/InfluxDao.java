package com.monitor.dao;

import com.monitor.util.InfluxDBManager;
import org.influxdb.InfluxDB;
import org.influxdb.annotation.Measurement;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author : ys
 * @date : 2019/4/16 15:12 星期二
 **/

//@Component	//不注释启动会报错，创建实例空指针异常
public class InfluxDao<T> {

	final InfluxDBMapper mapper = InfluxDBManager.getInfluxDBMapper();
	final InfluxDB influxDB = InfluxDBManager.getInfluxDB();

	String database;
	String measurement;

	Class<T> clazz;

	public InfluxDao() {
		// this表示的子类，c表示就是CustomerDaoImpl的Class对象
		Class c = this.getClass();
		// CustomerDaoImpl extends BaseDaoImpl<Customer>  map<k,v>
		// 第2步：获取到是BaseDaoImpl<Customer>
		Type type = c.getGenericSuperclass();

		// 目的：把type接口转换成子接口
		if (type instanceof ParameterizedType) {
			ParameterizedType ptype = (ParameterizedType) type;
			// 获取到 Customer
			Type[] types = ptype.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}

		database = getDatabaseName(clazz);
		measurement = getMeasurementName(clazz);
	}


	private String getDatabaseName(final Class<T> clazz) {
		return clazz.getAnnotation(Measurement.class).database();
	}

	private String getMeasurementName(final Class<T> clazz) {
		return clazz.getAnnotation(Measurement.class).name();
	}




	/**
	 * 查询全部数据
	 *
	 * @return
	 */
	public List<T> selectAll() {
		return mapper.query(clazz);
	}

	/**
	 * 分页查询
	 *
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */
	public List<T> pageQuery(int pageSize, int pageNo) {
		String sql = String.format("select * from %s limit %d offset %d", measurement, pageSize, pageSize * (pageNo - 1));
		String database = getDatabaseName(clazz);
		Query query = new Query(sql, database);
		return mapper.query(query, clazz);
	}


	/**
	 * 插入一条对象记录
	 *
	 * @param t
	 */
	public void save(T t) {
		mapper.save(t);
	}


	/**
	 * 删除表
	 *
	 * @return
	 */
	public boolean dropMeasurement() {
		String measurement = getMeasurementName(clazz);
		String database = getDatabaseName(clazz);
		String sql = String.format("drop measurement %s", measurement);
		QueryResult result = query(new Query(sql, database));
		return !result.hasError();
	}

	/**
	 * 执行查询语句
	 *
	 * @param query
	 * @return
	 */
	public QueryResult query(final Query query) {
		return influxDB.query(query);
	}
}
