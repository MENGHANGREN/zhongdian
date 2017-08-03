package com.three.zhongdian.mapper;

import com.three.zhongdian.po.BigType;
import com.three.zhongdian.po.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    public List<Book> findBookAll();
    public List<BigType> findTypeAll();
    public List<BigType> findBigType();

}
