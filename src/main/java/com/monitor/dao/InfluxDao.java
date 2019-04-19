package com.monitor.dao;

import com.monitor.pojo.Table2;
import com.monitor.util.InfluxdbBuilder;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.impl.InfluxDBMapper;
import org.junit.Test;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/4/16 15:12 星期二
 **/
public class InfluxDao {
	public Table2 getLast(){
		InfluxDB influxDB = InfluxdbBuilder.createInfluxDB();
		InfluxDBMapper mapper = new InfluxDBMapper(influxDB);
		String sql = "select last(*) from table2";
		Query query = new Query(sql);
		List<Table2> list = mapper.query(Table2.class);
		for (Table2 table2:list)
			System.out.println(table2);
		return list.get(0);
	}

	@Test
	public void test(){
		getLast();
	}
}
