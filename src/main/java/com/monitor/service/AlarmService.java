package com.monitor.service;

import com.monitor.pojo.Alarm;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/18
 */
public interface AlarmService {
    /**
     * 读取所有的报警规则
     * @return
     */
    List<Alarm> selectAll();

    /**
     * 通过报警Id来查询相应的报警规则
     * @param id
     * @return
     */
    Alarm selectById(Integer id);

    /**
     * 插入一条规则
     * @param alarm
     * @return
     */
    Integer insertAlarm(Alarm alarm);

    /**
     * 通过Id来删除报警规则
     * @param id
     * @return
     */
    void delete(Integer id);

    /**
     * 跟新报警规则
     * @param alarm
     */
    void update(Alarm alarm);
}
