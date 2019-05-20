package com.monitor.dao;

import com.monitor.pojo.Table;
import org.influxdb.dto.Query;
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
}
