package com.monitor.measurement;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * @author : ys
 * @date : 2019/10/10 16:22 星期四
 **/


//压力变送器
@Measurement(name = "pressure", database = "monitorMS")
public class Pressure {

	@Column(name = "time")
	private Instant time;

	@Column(name = "partition", tag = true)
	private String partition;//所属储罐

	@Column(name = "equipNumber", tag = true)
	private String equipNumber;//设备编号

	@Column(name = "pressure")
	private double pressure;//测量参数（压力）

	public Pressure() {
	}

	public Pressure(String partition, String equipNumber, double pressure) {
		this.partition = partition;
		this.equipNumber = equipNumber;
		this.pressure = pressure;
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public String getPartition() {
		return partition;
	}

	public void setPartition(String partition) {
		this.partition = partition;
	}

	public String getEquipNumber() {
		return equipNumber;
	}

	public void setEquipNumber(String equipNumber) {
		this.equipNumber = equipNumber;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	@Override
	public String toString() {
		return "Pressure{" +
				"time=" + time +
				", partition='" + partition + '\'' +
				", equipNumber='" + equipNumber + '\'' +
				", pressure=" + pressure +
				'}';
	}
}
