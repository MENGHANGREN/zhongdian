package com.three.zhongdian.user.service.impl;

import com.three.zhongdian.user.entity.User;
import com.three.zhongdian.user.mapper.UserMapper;
import com.three.zhongdian.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by admin on 2017/8/5.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        List<User> list = userMapper.login(user);
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }
}
