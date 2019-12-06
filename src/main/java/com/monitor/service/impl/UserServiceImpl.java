package com.monitor.service.impl;

import com.monitor.dao.alarm.UserDao;
import com.monitor.pojo.User;
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
    private UserDao userDao;

    @Override
    public List<User> selectAll() {
        return userDao.selectAll();
    }

	@Override
	public String getEmailByUsername(String username) {
		return null;
	}

}
