package com.three.zhongdian.author.service.impl;

import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.author.mapper.AuthorMapper;
import com.three.zhongdian.author.service.AuthorService;
import com.three.zhongdian.book.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 张艳恒 on 2017/8/7.
 */
@Service
public class AuthorServiceImpl implements AuthorService{

    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public Author findByAuthorName(String name) {
        return authorMapper.findByAuthorName(name);
    }

    @Override
    public List<Book> selectByAuthorName(String name) {
        return authorMapper.selectByAuthorName(name);
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 任梦杭 on 2017/08/11.
 */
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;
    @Override
    public void addAuthor(Author author) {
        authorMapper.addAuthor(author);
    }
}
