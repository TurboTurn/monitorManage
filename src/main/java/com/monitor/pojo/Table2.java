package com.monitor.pojo;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;

/**
 * @Author : ys
 * @Date : 2019/4/8 15:38 星期一
 **/
@Measurement(name = "table2", database = "ys")
public class Table2 {
	@Column(name = "time")
	private Instant time;
	@Column(name = "host", tag = true)
	private String hostname;
	@Column(name = "region", tag = true)
	private String region;
	@Column(name = "happydevop")
	private Boolean happyDevop;
	@Column(name = "idle")
	private Double idle;

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Boolean getHappyDevop() {
		return happyDevop;
	}

	public void setHappyDevop(Boolean happyDevop) {
		this.happyDevop = happyDevop;
	}

	public Double getIdle() {
		return idle;
	}

	public void setIdle(Double idle) {
		this.idle = idle;
	}

	@Override
	public String toString() {
		return "Table2{" +
				"time=" + time +
				", hostname='" + hostname + '\'' +
				", region='" + region + '\'' +
				", happyDevop=" + happyDevop +
				", idle=" + idle +
				'}';
	}
}
