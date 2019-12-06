package com.monitor.pojo;

import java.io.Serializable;

/**
 * 报警规则表
 * @author Created by Divo
 * @date 2019/11/18
 */
public class AlarmRule implements Serializable {

    private Integer id;
    private String tankID;
    private float heightH;
    private float heightHh;
    private float pressureH;
    private float pressureHh;
    private float temperatureH;
    private float temperatureHh;
    private Integer notifyType;
    private String notifier;

    @Override
    public String toString() {
        return "AlarmRule{" +
                "id=" + id +
                ", tankID='" + tankID + '\'' +
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

    public String getTankID() {
        return tankID;
    }

    public void setTankID(String tankID) {
        this.tankID = tankID;
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
