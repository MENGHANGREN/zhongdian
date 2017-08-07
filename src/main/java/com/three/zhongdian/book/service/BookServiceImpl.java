package com.three.zhongdian.book.service;

import com.three.zhongdian.book.mapper.BookMapper;
import com.three.zhongdian.book.po.BigType;
import com.three.zhongdian.book.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> findBookAll() {
        return bookMapper.findBookAll();
    }

    @Override
    public List<BigType> findTypeAll() {
        return bookMapper.findTypeAll();
    }

    @Override
    public List<BigType> findBigType() {
        return bookMapper.findBigType();
    }

    @Override
    public List<Book> findBookByType(int typeId) {
        return bookMapper.findBookByType(typeId);
    }

    @Override
    public BigType findTypeById(int id) {
        return bookMapper.findTypeById(id);
    }

    @Override
    public List<Book> findBookByMap(Map<String, Object> map) {
        return bookMapper.findBookByMap(map);
    }
}
