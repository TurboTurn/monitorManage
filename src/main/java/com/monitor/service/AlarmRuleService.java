package com.monitor.service;

import com.monitor.pojo.AlarmRule;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/18
 */
public interface AlarmRuleService {
    /**
     * 读取所有的报警规则
     * @return
	 */
	List<AlarmRule> selectAll();

	/**
	 * 通过报警Id来查询相应的报警规则
	 *
	 * @param id
	 * @return
	 */
	AlarmRule selectById(Integer id);

	Integer count();

	List<AlarmRule> listQuery(Integer pageNo, Integer pageSize);

	/**
	 * 插入一条规则
	 *
	 * @param alarmRule
	 * @return
	 */
	Integer insertAlarmRule(AlarmRule alarmRule);

	/**
	 * 通过Id来删除报警规则
	 *
	 * @param id
	 * @return
	 */
	Integer delete(Integer id);

	/**
	 * 跟新报警规则
	 *
	 * @param alarmRule
	 */
	Integer update(AlarmRule alarmRule);
}
