package com.three.zhongdian.service;

import com.three.zhongdian.po.BigType;
import com.three.zhongdian.po.Book;

import java.util.List;

public interface BookService {
    public List<Book> findBookAll();
    public List<BigType> findTypeAll();
    public List<BigType> findBigType();
}
