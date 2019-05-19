package com.monitor.dao;

import com.monitor.pojo.Table;
import com.monitor.util.InfluxDBManager;
import com.monitor.util.InfluxdbBuilder;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBMapper;
import org.junit.Test;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : ys
 * @date : 2019/4/16 15:12 星期二
 **/

@Component
public class InfluxDao {
	public void getLast(){
		InfluxDBMapper mapper = InfluxDBManager.getInfluxDBMapper();
		String sql = "select last(idle) from table";
		Query query = new Query(sql,"ys");
		QueryResult queryResult = InfluxDBManager.query(query);
		System.out.println(queryResult);
	}

	@Test
	public void test(){
		getLast();
	}

	/**
	 * 查询全部数据
	 * @return
	 */
	public <T> List<T> selectAll(Class<T> clazz){
		InfluxDBMapper mapper = InfluxDBManager.getInfluxDBMapper();
		return mapper.query(clazz);
	}

	/**
	 * 分页查询
	 */
	@Test
	public void pageQuery(){
		int pageSize = 10;
		int pageNo = 3;
		InfluxDBMapper mapper = InfluxDBManager.getInfluxDBMapper();
		String sql1 = "select * from table limit " + pageSize + " offset " + pageSize*(pageNo-1);
		Query query = new Query(sql1,"ys");
		List<Table> list = mapper.query(query, Table.class);
		for (Table table : list){
			System.out.println(table);
		}
	}

	/**
	 * 对象插入
	 */
	@Test
	public void singleInsert(){
		InfluxDBMapper mapper = InfluxDBManager.getInfluxDBMapper();
		Table model = new Table();
//		model.setTime(Instant.now());//save方法自动插入时间
		model.setHostname("localhost");
		model.setHappyDevop(true);
		model.setRegion("ezhou");
		model.setIdle(15.34);
		mapper.save(model);

	}

	/**
	 * 对象间隔插入
	 */
	@Test
	public void manyInsert() throws InterruptedException {
		InfluxDBMapper mapper = InfluxDBManager.getInfluxDBMapper();
		Table model = new Table();
		model.setHostname("localhost");
		model.setRegion("wuhan");
		int i=1000;
		boolean flag = true;
		while (i>0){
			model.setHappyDevop(flag);
			model.setIdle(10*Math.random());
			mapper.save(model);
			i--;
			flag = !flag;
			TimeUnit.SECONDS.sleep(2);
		}
	}



	@Test
	public void dropMeasurement(){
		String measurement = "table1";
		String sql = String.format("drop measurement %s",measurement);
		QueryResult result = InfluxDBManager.query(new Query(sql, "ys"));
		System.out.println(result.hasError());
	}
}
