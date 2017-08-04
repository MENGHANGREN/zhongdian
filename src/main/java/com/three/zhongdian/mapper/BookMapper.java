package com.three.zhongdian.mapper;

import com.three.zhongdian.po.BigType;
import com.three.zhongdian.po.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BookMapper {
    public List<Book> findBookAll();
    public List<BigType> findTypeAll();
    public List<BigType> findBigType();
    public List<Book> findBookByType(int typeId);
    public BigType findTypeById(int id);
    public List<Book> findBookByMap(Map<String,Object> map);

}
