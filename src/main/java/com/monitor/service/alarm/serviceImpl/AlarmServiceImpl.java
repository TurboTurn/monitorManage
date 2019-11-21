package com.monitor.service.alarm.serviceImpl;

import com.monitor.dao.alarm.AlarmDao;
import com.monitor.pojo.Alarm;
import com.monitor.service.alarm.AlarmService;
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
       int row=alarmDao.insertAlarm(alarm);
       System.out.println("添加的报警记录的Id为: " + alarm.getId());
       return alarm.getId();
    }

    @Override
    public void del(Integer id) {
        alarmDao.del(id);
    }

    @Override
    public void update(Alarm alarm) {
        alarmDao.update(alarm);
    }
}
