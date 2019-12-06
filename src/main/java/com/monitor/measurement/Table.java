package com.monitor.measurement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.io.Serializable;
import java.time.Instant;
import java.time.ZoneId;

/**
 * @Author : ys
 * @Date : 2019/4/8 15:38 星期一
 **/
@ApiModel(description = "table表结构对应的实体")
@Measurement(name = "factory1", database = "monitorMS")
public class Table {//必须实现无参构造函数和get set方法才能使用JSON.toJSONString()
	@ApiModelProperty(value = "时间戳")
	@Column(name = "time")
	private Instant time;

	@ApiModelProperty(value = "油罐")
	@Column(name = "a1_tank", tag = true)
	private String tank;

	@ApiModelProperty(value = "油品")
	@Column(name = "a2_oil", tag = true)
	private String oil;

	@ApiModelProperty(value = "液位高度")
	@Column(name = "height_ld")
	private double height;

	@ApiModelProperty(value = "压强")
	@Column(name = "pressure")
	private double pressure;

	@ApiModelProperty(value = "温度")
	@Column(name = "temperature")
	private double temperature;

	@ApiModelProperty(value = "进料阀门")
	@Column(name = "valve_in")
	private int valve_in;

	@ApiModelProperty(value = "出料阀门")
	@Column(name = "valve_out")
	private int valve_out;


	public Table() {
	}
	public Table(Instant time, String tank, String oil, double height, double pressure, double temperature, int valve_in, int valve_out) {
		this.time = time;
		this.tank = tank;
		this.oil = oil;
		this.height = height;
		this.pressure = pressure;
		this.temperature = temperature;
		this.valve_in = valve_in;
		this.valve_out = valve_out;
	}


	public Instant getTime() {
		return time.atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))).toInstant();
	}

//	public Instant getTime() {
//		return time;
//	}

	public void setTime(Instant time) {
		this.time = time.atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))).toInstant();
	}

	public String getTank() {
		return tank;
	}

	public void setTank(String tank) {
		this.tank = tank;
	}

	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getPressure() {
		return pressure;
	}

	public void setPressure(double pressure) {
		this.pressure = pressure;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getValve_in() {
		return valve_in;
	}

	public void setValve_in(int valve_in) {
		this.valve_in = valve_in;
	}

	public int getValve_out() {
		return valve_out;
	}

	public void setValve_out(int valve_out) {
		this.valve_out = valve_out;
	}

	@Override
	public String toString() {
		return "Table{" +
				"time=" + time +
				", tank='" + tank + '\'' +
				", oil='" + oil + '\'' +
				", height=" + height +
				", pressure=" + pressure +
				", temperature=" + temperature +
				", valve_in=" + valve_in +
				", valve_out=" + valve_out +
				'}';
	}
}
