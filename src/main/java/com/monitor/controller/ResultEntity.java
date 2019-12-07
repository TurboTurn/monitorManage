package com.monitor.controller;

public class ResultEntity {//需实现get set才能将对象转化为json返回给前端
	private int resultCode;
	private Object resultMsg;

	public ResultEntity() {
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public Object getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(Object resultMsg) {
		this.resultMsg = resultMsg;
	}

	public ResultEntity(int resultCode, Object resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public static ResultEntity success(Object object) {
		return new ResultEntity(0, object);
	}

	public static ResultEntity fail(Object object) {
		return new ResultEntity(1, object);
	}
}