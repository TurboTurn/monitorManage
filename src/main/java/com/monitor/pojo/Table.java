package com.monitor.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;
import java.time.ZoneId;

/**
 * @Author : ys
 * @Date : 2019/4/8 15:38 星期一
 **/
@ApiModel(description = "table表结构对应的实体")
@Measurement(name = "table1", database = "ys")
public class Table {
	@ApiModelProperty(value = "时间戳")
	@Column(name = "time")
	private Instant time;

	@ApiModelProperty(value = "主机名")
	@Column(name = "host", tag = true)
	private String hostname;

	@ApiModelProperty(value = "地区")
	@Column(name = "region", tag = true)
	private String region;

	@ApiModelProperty(value = "快乐")
	@Column(name = "happydevop")
	private Boolean happyDevop;

	@ApiModelProperty(value = "记录数值")
	@Column(name = "idle")
	private Double idle;

//	public Instant getTime() {
//		return time;
//	}
	public Instant getTime() {
		return time.atZone(ZoneId.of(ZoneId.SHORT_IDS.get("CTT"))).toInstant();
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
		return "Table{" +
				"time=" + time +
				", hostname='" + hostname + '\'' +
				", region='" + region + '\'' +
				", happyDevop=" + happyDevop +
				", idle=" + idle +
				'}';
	}
}
