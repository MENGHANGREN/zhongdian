package com.three.zhongdian.author.service;

import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.book.po.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by 张艳恒 on 2017/8/7.
 */
@Transactional
@Component
public interface AuthorService {
    /*
    根据作者查询
     */
    @Transactional(readOnly =true)
    Author findByAuthorName(String name);
    @Transactional(readOnly =true)
    List<Book> selectByAuthorName(String name);

}
