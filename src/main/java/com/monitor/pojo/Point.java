package com.monitor.pojo;

/**
 * @author : ys
 * @date : 2019/5/8 14:41 星期三
 **/
public class Point {//转JSON字符串时要写get set 方法
	private long x;
	private double y;

	public Point() {
	}

	public Point(long x, double y) {
		this.x = x;
		this.y = y;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "{x:" + x +
				",y:" + y +
				'}';
	}
}
