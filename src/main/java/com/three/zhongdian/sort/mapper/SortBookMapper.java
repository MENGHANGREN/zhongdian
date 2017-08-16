package com.three.zhongdian.sort.mapper;

import com.three.zhongdian.sort.pojo.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhx on 2017/8/13.
 */
@Repository
@Mapper
public interface SortBookMapper {

    public List<Book> getBooksByType(Integer typeId);
    public List<Book> getBooksByTiket();
    public List<Book> getBooksByTiketTop10();
}
