package com.monitor.dao.alarm;

import com.monitor.pojo.AlarmRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/18
 */
@Mapper//使用Mapper则不需包扫描@MapperScan(basePackages = {"com.xxx.xxx"})来扫描
@Repository
public interface AlarmRuleDao {

    List<AlarmRule> selectAll();

    AlarmRule selectById(Integer id);

    Integer insertAlarmRule(AlarmRule alarmRule);

    Integer delete(Integer id);

    Integer update(AlarmRule alarmRule);

    Integer count();

    List<AlarmRule> listQuery(@Param("start") Integer start, @Param("size") Integer size);
}
