package com.monitor.service.impl;

import com.monitor.dao.alarm.AlarmHistoryDao;
import com.monitor.pojo.AlarmHistory;
import com.monitor.service.AlarmHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by Divo
 * @date 2019/11/20
 */
@Service
public class AlarmHistoryServiceImpl implements AlarmHistoryService {

    @Autowired
    private AlarmHistoryDao alarmRecordDao;

    /**
     * 插入已经监控的报警数据到alarmList表中
     * @param alarmHistory
     * @return
     */
    @Override
    public Integer insertAlarmHistory(AlarmHistory alarmHistory) {
        return alarmRecordDao.insertAlarmHistory(alarmHistory);
    }
}
