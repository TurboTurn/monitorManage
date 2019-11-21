package com.monitor.dao.alarm;

import com.monitor.pojo.AlarmRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 自动生成
 * @author Created by Divo
 * @date 2019/11/20
 */

@Mapper
@Repository
public interface AlarmRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insertAlarmRecord(AlarmRecord record);

    int insertSelective(AlarmRecord record);

    AlarmRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmRecord record);

    int updateByPrimaryKey(AlarmRecord record);
}