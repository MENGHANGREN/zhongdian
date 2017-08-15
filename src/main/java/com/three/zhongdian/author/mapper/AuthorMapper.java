package com.three.zhongdian.author.mapper;

import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.book.po.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by admin on 2017/8/7.
 */
@Mapper
@Component
public interface AuthorMapper {


    /*
        根据作者查询
         */
    //@Select("select * from author where name=#{name} ")
    Author  findByAuthorName(String name);

   // @Select("select a.*, b.name ,b.words ,b.section ,b.click  ,b.picpath ,b.filepath ,b.info  from author a,book b where a.id=b.aid and  a.name=#{name} ")
    List<Book> selectByAuthorName(String name);



}
