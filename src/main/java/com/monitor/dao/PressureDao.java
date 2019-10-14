package com.monitor.dao;

import com.monitor.measurement.Pressure;
import org.influxdb.dto.Query;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author : ys
 * @date : 2019/10/10 16:44 星期四
 **/

@Component
public class PressureDao extends InfluxDao<Pressure> {
	public PressureDao() {
	}

	public Pressure getLast(){
		String sql = String.format("select last(pressure) as pressure,partition,equipNumber from %s where partition = 'CG1' " , measurement);
		Query query = new Query(sql, database);
		List<Pressure> list = mapper.query(query, clazz);
		if(list.size() > 0)
			return list.get(0);
		else return null;
	}

	public static void main(String[] args) {
		PressureDao pressureDao = new PressureDao();
//		pressureDao.save(new Pressure("CG1", "YB-01", 8.7));
//		List<Pressure> list = pressureDao.selectAll();
//		list.forEach(System.out::println);
		System.out.println(pressureDao.getLast());
	}
}
