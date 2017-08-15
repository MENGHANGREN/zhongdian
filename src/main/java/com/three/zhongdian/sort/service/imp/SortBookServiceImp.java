package com.three.zhongdian.sort.service.imp;

import com.three.zhongdian.sort.pojo.Book;
import com.three.zhongdian.sort.mapper.SortBookMapper;
import com.three.zhongdian.sort.service.SortBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhx on 2017/8/13.
 */
@Service
public class SortBookServiceImp implements SortBookService {
    @Autowired
    private SortBookMapper sortBookMapper;

    @Override
    public List<Book> getBooksByType(Integer typeId) {
        return sortBookMapper.getBooksByType(typeId);
    }

    @Override
    public List<Book> getBooksByTiket() {
        return sortBookMapper.getBooksByTiket();
    }

    @Override
    public List<Book> getBooksByTiketTop10() {
        return sortBookMapper.getBooksByTiketTop10();
    }
}
