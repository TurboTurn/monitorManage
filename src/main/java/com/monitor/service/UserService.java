package com.monitor.service;


import com.monitor.pojo.UserInfo;

import java.util.List;

/**
 * 用来查询用户信息
 * @author Created by Divo
 * @date 2019/11/19
 */
public interface UserService {

    List<UserInfo> selectAll();
}
