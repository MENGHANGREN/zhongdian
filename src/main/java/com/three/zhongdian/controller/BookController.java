package com.three.zhongdian.controller;

import com.three.zhongdian.po.BigType;
import com.three.zhongdian.po.Book;
import com.three.zhongdian.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/list")
    public ModelAndView list(){
        List<Book> books = bookService.findBookAll();
        System.out.println(books.get(0).toString());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        return mv;
    }


    @RequestMapping("/findType")
    @ResponseBody
    public List<BigType> findType(){
        List<BigType> bigTypes = bookService.findBigType();
        System.out.println(bigTypes);
        return bigTypes;
    }
}
