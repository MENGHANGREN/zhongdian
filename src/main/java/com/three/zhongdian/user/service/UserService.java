package com.three.zhongdian.user.service;

import com.three.zhongdian.user.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by admin on 2017/8/5.
 */
public interface UserService {
    /*
    登陆的方法
     */
    User login(User user);
}
