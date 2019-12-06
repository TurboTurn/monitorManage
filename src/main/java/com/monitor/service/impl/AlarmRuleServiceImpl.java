package com.monitor.service.impl;

import com.monitor.dao.alarm.AlarmRuleDao;
import com.monitor.pojo.AlarmRule;
import com.monitor.service.AlarmRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/18
 */
@Service
public class AlarmRuleServiceImpl implements AlarmRuleService {

    @Autowired
    private AlarmRuleDao alarmRuleDao;

    @Override
    public List<AlarmRule> selectAll() {
        return alarmRuleDao.selectAll();
    }

    @Override
    public AlarmRule selectById(Integer id) {
        return alarmRuleDao.selectById(id);
    }

    @Override
    public Integer insertAlarm(AlarmRule alarmRule) {
       return alarmRuleDao.insertAlarm(alarmRule);
    }

    @Override
    public void delete(Integer id) {
        alarmRuleDao.delete(id);
    }

    @Override
    public void update(AlarmRule alarmRule) {
        alarmRuleDao.update(alarmRule);
    }
}
