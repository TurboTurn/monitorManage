package com.monitor.pojo;

import java.io.Serializable;

/**
 * 报警规则表
 * @author Created by Divo
 * @date 2019/11/18
 */
public class Alarm implements Serializable {

    private Integer id;
    private String tank;
    private String oil;
    private double height;
    private double pressure;
    private double temperature;
    private String username;

    public Alarm(Integer id, String tank, String oil, float height, float pressure, float temperature, String username) {
        this.id = id;
        this.tank = tank;
        this.oil = oil;
        this.height = height;
        this.pressure = pressure;
        this.temperature = temperature;
        this.username = username;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", tank='" + tank + '\'' +
                ", oil='" + oil + '\'' +
                ", height=" + height +
                ", pressure=" + pressure +
                ", temperature=" + temperature +
                ", username='" + username + '\'' +
                '}';
    }
}
