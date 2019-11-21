package com.monitor.pojo;

import java.util.Date;

/**
 * 报警记录表
 * @author Created by Divo
 * @date 2019/11/19
 */
public class AlarmRecord {

    private Integer id;
    private String tank;
    private String oil;
    private double heightLd;
    private double heightSf1;
    private double heightSf2;
    private double pressure;
    private double temperature;
    /**
     * 报警处理的时间
     */
    private Date happendTime;
    /**
     * 此报警记录违反了alarm表中那一条报警规则
     */
    private Integer alarmId;

    public AlarmRecord() {
    }

    public AlarmRecord(Integer id, String tank, String oil, double heightLd, double heightSf1, double heightSf2, double pressure, double temperature, Date happendTime, Integer alarmId) {
        this.id = id;
        this.tank = tank;
        this.oil = oil;
        this.heightLd = heightLd;
        this.heightSf1 = heightSf1;
        this.heightSf2 = heightSf2;
        this.pressure = pressure;
        this.temperature = temperature;
        this.happendTime = happendTime;
        this.alarmId = alarmId;
    }

    public void setHappendTime(Date happendTime) {
        this.happendTime = happendTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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


    public double getHeightLd() {
        return heightLd;
    }

    public void setHeightLd(double heightLd) {
        this.heightLd = heightLd;
    }

    public double getHeightSf1() {
        return heightSf1;
    }

    public void setHeightSf1(double heightSf1) {
        this.heightSf1 = heightSf1;
    }

    public double getHeightSf2() {
        return heightSf2;
    }

    public void setHeightSf2(double heightSf2) {
        this.heightSf2 = heightSf2;
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

    public Date getHappendTime() {
        return happendTime;
    }
    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    @Override
    public String toString() {
        return "AlarmRecord{" +
                "id=" + id +
                ", tank='" + tank + '\'' +
                ", oil='" + oil + '\'' +
                ", heightLd=" + heightLd +
                ", heightSf1=" + heightSf1 +
                ", heightSf2=" + heightSf2 +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", happendTime=" + happendTime +
                ", alarmId=" + alarmId +
                '}';
    }
}
