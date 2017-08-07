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
     List<Book> findBookAll();
     List<BigType> findTypeAll();
     List<BigType> findBigType();
     List<Book> findBookByType(int typeId);
     BigType findTypeById(int id);
     List<Book> findBookByMap(Map<String,Object> map);
    Book findBookById(int id);
    List<Book> findBookByName(String name);

}
