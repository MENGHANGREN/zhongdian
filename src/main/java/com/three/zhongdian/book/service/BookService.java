package com.three.zhongdian.book.service;

import com.three.zhongdian.book.po.BigType;
import com.three.zhongdian.book.po.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    public List<Book> findBookAll();
    public List<BigType> findTypeAll();
    public List<BigType> findBigType();
    public List<Book> findBookByType(int typeId);
    public BigType findTypeById(int id);
    public List<Book> findBookByMap(Map<String,Object> map);
}
