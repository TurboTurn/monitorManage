package com.monitor.dao.alarm;

import com.monitor.pojo.AlarmHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 自动生成
 * @author Created by Divo
 * @date 2019/11/20
 */

@Mapper
@Repository
public interface AlarmHistoryDao {

    int insertAlarmHistory(AlarmHistory record);

    AlarmHistory selectByPrimaryKey(Integer id);

}