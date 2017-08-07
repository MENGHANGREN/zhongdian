package com.three.zhongdian.user.mapper;

import com.three.zhongdian.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by admin on 2017/8/5.
 */
@Mapper
@Repository
public interface UserMapper {
    /*
    登陆的方法
     */
    @Select("select * from user where (username=#{username} or mail=#{username} or phone=#{username}) and password=#{password}")
    List<User> login(User user);

}
