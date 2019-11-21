package com.monitor.service.alarm.serviceImpl;

import com.monitor.dao.alarm.AlarmRecordDao;
import com.monitor.pojo.AlarmRecord;
import com.monitor.service.alarm.AlarmRecordService;
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
    public Integer InsertAlarmRecord(AlarmRecord alarmRecord) {
        int row = alarmRecordDao.insertAlarmRecord(alarmRecord);
        System.out.println("保存的alarmRecord记录的Id: "+alarmRecord.getId());
        return alarmRecord.getId();
    }
}
