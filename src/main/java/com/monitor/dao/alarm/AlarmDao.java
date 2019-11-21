package com.monitor.dao.alarm;

import com.monitor.pojo.Alarm;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/18
 */
@Mapper
@Repository
public interface AlarmDao {

    List<Alarm> selectAll();

    Alarm selectById(Integer id);

    Integer insertAlarm(Alarm alarm);

    void delete(Integer id);

    void update(Alarm alarm);
}
