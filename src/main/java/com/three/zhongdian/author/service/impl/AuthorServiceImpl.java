package com.three.zhongdian.author.service.impl;

import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.author.mapper.AuthorMapper;
import com.three.zhongdian.author.service.AuthorService;
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
