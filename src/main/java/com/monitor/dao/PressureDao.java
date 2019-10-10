package com.monitor.dao;

import com.monitor.measurement.Pressure;
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

	public static void main(String[] args) {
		PressureDao pressureDao = new PressureDao();
		pressureDao.save(new Pressure("CG1", "YB-01", 8.7));
		List<Pressure> list = pressureDao.selectAll();
		list.forEach(System.out::println);
	}
}
