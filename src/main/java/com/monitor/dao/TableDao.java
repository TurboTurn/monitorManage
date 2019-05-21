package com.monitor.dao;

import com.monitor.pojo.Table;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.junit.Test;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/20 17:19 星期一
 **/
@Component
public class TableDao extends InfluxDao<Table> {

	public TableDao() {

	}

	/**
	 * 获取最后一条记录
	 * @return
	 */
	public Table getLast() {
		String sql = String.format("select last(idle) as idle,host,region from %s", measurement);
		Query query = new Query(sql, database);
		List<Table> list = mapper.query(query, clazz);
		return list.get(0);
	}

	/**
	 * 返回一个（field）字段中的非空值的数量。
	 * @return
	 */
	public int count(){
		String sql = "select count(idle) from table1";
		Query query = new Query(sql,"ys");
		QueryResult result = influxDB.query(query);
		//QueryResult [results=[Result [series=[Series [name=table1, tags=null, columns=[time, count], values=[[1970-01-01T00:00:00Z, 24.0]]]], error=null]], error=null]
		double count = (double)result.getResults().get(0).getSeries().get(0).getValues().get(0).get(1);
		return (int)count;
	}
}
