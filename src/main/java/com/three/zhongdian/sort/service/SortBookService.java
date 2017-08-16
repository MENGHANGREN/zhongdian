package com.three.zhongdian.sort.service;

import com.three.zhongdian.sort.pojo.Book;

import java.util.List;

/**
 * Created by zhx on 2017/8/13.
 */
public interface SortBookService {

    public List<Book> getBooksByType(Integer typeId);
    public List<Book> getBooksByTiket();
    public List<Book> getBooksByTiketTop10();
}
