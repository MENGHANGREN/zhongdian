package com.three.zhongdian.author.service;

import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.book.po.Book;
import java.util.List;

/**
 * Created by 任梦杭 on 2017/08/11.
 */
public interface AuthorService {
    void addAuthor(Author author);
    /*
    根据作者查询
     */
    Author findByAuthorName(String name);
    List<Book> selectByAuthorName(String name);
}
