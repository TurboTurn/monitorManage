package com.monitor.dao.alarm;

import com.monitor.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 自动生成
 * @author Created by Divo
 * @date 2019/11/20
 */
@Mapper
@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    String getEmailByUsername(String username);
}