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
	public Integer count() {
		return alarmRuleDao.count();
	}

	@Override
	public List<AlarmRule> listQuery(Integer pageNo, Integer pageSize) {
		return alarmRuleDao.listQuery((pageNo - 1) * pageSize, pageSize);
	}

	@Override
	public Integer insertAlarmRule(AlarmRule alarmRule) {
		return alarmRuleDao.insertAlarmRule(alarmRule);
	}

	@Override
	public Integer delete(Integer id) {
		return alarmRuleDao.delete(id);
	}

	@Override
	public Integer update(AlarmRule alarmRule) {
		return alarmRuleDao.update(alarmRule);
	}
}
