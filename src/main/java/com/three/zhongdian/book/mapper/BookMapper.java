package com.three.zhongdian.book.mapper;

import com.three.zhongdian.book.po.BigType;
import com.three.zhongdian.book.po.Book;
import com.three.zhongdian.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BookMapper {
    public List<Book> findBookAll();
    public List<BigType> findTypeAll();
    public List<BigType> findBigType();
    public List<Book> findBookByType(int typeId);
    public BigType findTypeById(int id);
    public List<Book> findBookByMap(Map<String,Object> map);

}
