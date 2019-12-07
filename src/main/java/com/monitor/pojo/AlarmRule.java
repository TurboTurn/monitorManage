package com.monitor.pojo;

import java.io.Serializable;

/**
 * 报警规则表
 * @author Created by Divo
 * @date 2019/11/18
 */
public class AlarmRule implements Serializable {

    private Integer id;
	private String tankId;
	private float heightH;
	private float heightHh;
	private float pressureH;
	private float pressureHh;
	private float temperatureH;
	private float temperatureHh;
	private Integer notifyType;
	private String notifier;

	public AlarmRule() {
	}

	public AlarmRule(String tankId, float heightH, float heightHh, float pressureH, float pressureHh, float temperatureH, float temperatureHh, Integer notifyType, String notifier) {
		this.tankId = tankId;
		this.heightH = heightH;
		this.heightHh = heightHh;
		this.pressureH = pressureH;
		this.pressureHh = pressureHh;
		this.temperatureH = temperatureH;
		this.temperatureHh = temperatureHh;
		this.notifyType = notifyType;
		this.notifier = notifier;
	}

	@Override
	public String toString() {
		return "AlarmRule{" +
				"id=" + id +
				", tankId='" + tankId + '\'' +
				", heightH=" + heightH +
				", heightHh=" + heightHh +
				", pressureH=" + pressureH +
				", pressureHh=" + pressureHh +
				", temperatureH=" + temperatureH +
				", temperatureHh=" + temperatureHh +
				", notifyType=" + notifyType +
				", notifier='" + notifier + '\'' +
				'}';
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTankId() {
		return tankId;
	}

	public void setTankId(String tankId) {
		this.tankId = tankId;
	}

	public float getHeightH() {
		return heightH;
	}

	public void setHeightH(float heightH) {
		this.heightH = heightH;
	}

    public float getHeightHh() {
        return heightHh;
    }

    public void setHeightHh(float heightHh) {
        this.heightHh = heightHh;
    }

    public float getPressureH() {
        return pressureH;
    }

    public void setPressureH(float pressureH) {
        this.pressureH = pressureH;
    }

    public float getPressureHh() {
        return pressureHh;
    }

    public void setPressureHh(float pressureHh) {
        this.pressureHh = pressureHh;
    }

    public float getTemperatureH() {
        return temperatureH;
    }

    public void setTemperatureH(float temperatureH) {
        this.temperatureH = temperatureH;
    }

    public float getTemperatureHh() {
        return temperatureHh;
    }

    public void setTemperatureHh(float temperatureHh) {
        this.temperatureHh = temperatureHh;
    }

    public Integer getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(Integer notifyType) {
        this.notifyType = notifyType;
    }

    public String getNotifier() {
        return notifier;
    }

    public void setNotifier(String notifier) {
        this.notifier = notifier;
    }
}
