package com.three.zhongdian.author.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/8/7.
 */
@Mapper
@Repository
public interface AuthorMapper {
    @Insert("insert into author(name) value(#{name})")
    void insert(String name);
}
