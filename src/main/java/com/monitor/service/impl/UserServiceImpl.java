package com.monitor.service.impl;

import com.monitor.dao.alarm.UserInfoDao;
import com.monitor.pojo.UserInfo;
import com.monitor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Created by Divo
 * @date 2019/11/19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> selectAll() {
        return userInfoDao.selectAll();
    }

}
