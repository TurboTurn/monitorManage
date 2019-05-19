package com.monitor.service;

import com.monitor.dao.InfluxDao;
import com.monitor.pojo.Table;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/5/19 19:36 星期日
 **/
@Service
public class TableService {
	private InfluxDao influxDao;

	public TableService(InfluxDao influxDao) {
		this.influxDao = influxDao;
	}

	/**
	 * 查询全部数据
	 */
	public List<Table> selectAll(){
		List<Table> list = influxDao.selectAll(Table.class);
		return list;
	}

}
