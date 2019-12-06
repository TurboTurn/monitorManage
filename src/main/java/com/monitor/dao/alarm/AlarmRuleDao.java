package com.monitor.dao.alarm;

import com.monitor.pojo.AlarmRule;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/18
 */
@Mapper
@Repository
public interface AlarmRuleDao {

    List<AlarmRule> selectAll();

    AlarmRule selectById(Integer id);

    Integer insertAlarm(AlarmRule alarmRule);

    void delete(Integer id);

    void update(AlarmRule alarmRule);
}
