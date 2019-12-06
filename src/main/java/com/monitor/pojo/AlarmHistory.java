package com.monitor.pojo;

import java.util.Date;

public class AlarmHistory {
    private Integer id;
    private String  tankId;
    private Integer alarmRuleId;
    private String  alarmRule;
    private Date    alarmTime;
    private String  alarmData;
    private Integer alarmHandle;

    public AlarmHistory() {
    }

    @Override
    public String toString() {
        return "AlarmHistory{" +
                "id=" + id +
                ", tankId='" + tankId + '\'' +
                ", alarmRuleId=" + alarmRuleId +
                ", alarmRule='" + alarmRule + '\'' +
                ", alarmTime=" + alarmTime +
                ", alarmData='" + alarmData + '\'' +
                ", alarmHandle=" + alarmHandle +
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

    public Integer getAlarmRuleId() {
        return alarmRuleId;
    }

    public void setAlarmRuleId(Integer alarmRuleId) {
        this.alarmRuleId = alarmRuleId;
    }

    public String getAlarmRule() {
        return alarmRule;
    }

    public void setAlarmRule(String alarmRule) {
        this.alarmRule = alarmRule;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(String alarmData) {
        this.alarmData = alarmData;
    }

    public Integer getAlarmHandle() {
        return alarmHandle;
    }

    public void setAlarmHandle(Integer alarmHandle) {
        this.alarmHandle = alarmHandle;
    }
}
