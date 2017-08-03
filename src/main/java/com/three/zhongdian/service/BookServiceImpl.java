package com.three.zhongdian.service;

import com.three.zhongdian.mapper.BookMapper;
import com.three.zhongdian.po.BigType;
import com.three.zhongdian.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
