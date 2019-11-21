package com.monitor.service.impl;

import com.monitor.dao.alarm.AlarmRecordDao;
import com.monitor.pojo.AlarmRecord;
import com.monitor.service.AlarmRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by Divo
 * @date 2019/11/20
 */
@Service
public class AlarmRecordServiceImpl implements AlarmRecordService {

    @Autowired
    private AlarmRecordDao alarmRecordDao;

    /**
     * 插入已经监控的报警数据到alarmList表中
     * @param alarmRecord
     * @return
     */
    @Override
    public Integer insertAlarmRecord(AlarmRecord alarmRecord) {
        return alarmRecordDao.insertAlarmRecord(alarmRecord);
    }
}
