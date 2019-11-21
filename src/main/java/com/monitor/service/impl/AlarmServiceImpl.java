package com.monitor.service.impl;

import com.monitor.dao.alarm.AlarmDao;
import com.monitor.pojo.Alarm;
import com.monitor.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/18
 */
@Service
public class AlarmServiceImpl implements AlarmService {

    @Autowired
    private AlarmDao alarmDao;

    @Override
    public List<Alarm> selectAll() {
        return alarmDao.selectAll();
    }

    @Override
    public Alarm selectById(Integer id) {
        return alarmDao.selectById(id);
    }

    @Override
    public Integer insertAlarm(Alarm alarm) {
       return alarmDao.insertAlarm(alarm);
    }

    @Override
    public void delete(Integer id) {
        alarmDao.delete(id);
    }

    @Override
    public void update(Alarm alarm) {
        alarmDao.update(alarm);
    }
}
