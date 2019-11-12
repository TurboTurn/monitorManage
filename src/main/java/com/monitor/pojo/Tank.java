package com.monitor.pojo;

import java.io.Serializable;

public class Tank implements Serializable {
	private long time;
	private String a1_tank;
	private String a2_oil;
	private boolean valve;
	private double height_sf1;
	private double height_sf2;
	private double height_ld;
	private double pressure;
	private double temperature;

	public Tank() {
	}

	public Tank(long time, String a1_tank, String a2_oil, boolean valve, double height_sf1, double height_sf2, double height_ld, double pressure, double temperature) {
		this.time = time;
		this.a1_tank = a1_tank;
		this.a2_oil = a2_oil;
		this.valve = valve;
		this.height_sf1 = height_sf1;
		this.height_sf2 = height_sf2;
		this.height_ld = height_ld;
		this.pressure = pressure;
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "Tank{" +
				"time=" + time +
				", a1_tank='" + a1_tank + '\'' +
				", a2_oil='" + a2_oil + '\'' +
				", valve=" + valve +
				", height_sf1=" + height_sf1 +
				", height_sf2=" + height_sf2 +
				", height_ld=" + height_ld +
				", pressure=" + pressure +
				", temperature=" + temperature +
				'}';
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public String getA1_tank() {
		return a1_tank;
	}

	public void setA1_tank(String a1_tank) {
		this.a1_tank = a1_tank;
	}

	public String getA2_oil() {
		return a2_oil;
	}

	public void setA2_oil(String a2_oil) {
		this.a2_oil = a2_oil;
	}

	public boolean isValve() {
		return valve;
	}

	public void setValve(boolean valve) {
		this.valve = valve;
	}

	public double getHeight_sf1() {
		return height_sf1;
	}

	public void setHeight_sf1(double height_sf1) {
		this.height_sf1 = height_sf1;
	}

	public double getHeight_sf2() {
		return height_sf2;
	}

	public void setHeight_sf2(double height_sf2) {
		this.height_sf2 = height_sf2;
	}

	public double getHeight_ld() {
		return height_ld;
	}

	public void setHeight_ld(double height_ld) {
		this.height_ld = height_ld;
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
}
