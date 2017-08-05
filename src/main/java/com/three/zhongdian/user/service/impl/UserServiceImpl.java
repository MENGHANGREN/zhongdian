package com.three.zhongdian.user.service.impl;

import com.three.zhongdian.user.mapper.UserMapper;
import com.three.zhongdian.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/8/5.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

}
