package com.monitor.service;

import com.monitor.pojo.AlarmHistory;

/**
 * 对应于报警存储表
 * @author Created by Divo
 * @date 2019/11/20
 */
public interface AlarmHistoryService {

    Integer insertAlarmHistory(AlarmHistory alarmHistory);

}
