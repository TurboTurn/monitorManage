package com.monitor.pojo;

import java.io.Serializable;
import java.util.Objects;

public class Tank implements Serializable {
	private long time;
	private String a1_tank;
	private String a2_oil;
	private float height_ld;
	private float height_sf1;
	private float height_sf2;
	private float pressure;
	private float temperature;
	private int valve_in;
	private int valve_out;

	public Tank() {
	}

	public Tank(long time, String a1_tank, String a2_oil, float height_ld, float height_sf1, float height_sf2, float pressure, float temperature, int valve_in, int valve_out) {
		this.time = time;
		this.a1_tank = a1_tank;
		this.a2_oil = a2_oil;
		this.height_ld = height_ld;
		this.height_sf1 = height_sf1;
		this.height_sf2 = height_sf2;
		this.pressure = pressure;
		this.temperature = temperature;
		this.valve_in = valve_in;
		this.valve_out = valve_out;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Tank tank = (Tank) o;
		return Float.compare(tank.height_ld, height_ld) == 0 &&
				Float.compare(tank.height_sf1, height_sf1) == 0 &&
				Float.compare(tank.height_sf2, height_sf2) == 0 &&
				Float.compare(tank.pressure, pressure) == 0 &&
				Float.compare(tank.temperature, temperature) == 0 &&
				valve_in == tank.valve_in &&
				valve_out == tank.valve_out &&
				Objects.equals(a1_tank, tank.a1_tank) &&
				Objects.equals(a2_oil, tank.a2_oil);
	}

	@Override
	public int hashCode() {
		return Objects.hash(a1_tank, a2_oil, height_ld, height_sf1, height_sf2, pressure, temperature, valve_in, valve_out);
	}

	@Override
	public String toString() {
		return "Tank{" +
				"time=" + time +
				", a1_tank='" + a1_tank + '\'' +
				", a2_oil='" + a2_oil + '\'' +
				", height_ld=" + height_ld +
				", height_sf1=" + height_sf1 +
				", height_sf2=" + height_sf2 +
				", pressure=" + pressure +
				", temperature=" + temperature +
				", valve_in=" + valve_in +
				", valve_out=" + valve_out +
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

	public float getHeight_ld() {
		return height_ld;
	}

	public void setHeight_ld(float height_ld) {
		this.height_ld = height_ld;
	}

	public float getHeight_sf1() {
		return height_sf1;
	}

	public void setHeight_sf1(float height_sf1) {
		this.height_sf1 = height_sf1;
	}

	public float getHeight_sf2() {
		return height_sf2;
	}

	public void setHeight_sf2(float height_sf2) {
		this.height_sf2 = height_sf2;
	}

	public float getPressure() {
		return pressure;
	}

	public void setPressure(float pressure) {
		this.pressure = pressure;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(float temperature) {
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
}