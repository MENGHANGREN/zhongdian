package com.three.zhongdian.author.controller;

import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.author.service.AuthorService;
import com.three.zhongdian.book.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 张艳恒 on 2017/8/7.
 */
@Controller
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @RequestMapping("findByAuthorName")
    public String findByAuthorName(String name, Model model,HttpSession session){
        Author au = authorService.findByAuthorName(name);
        List<Book> list = authorService.selectByAuthorName(name);
        for (Book o : list) {
            System.out.println(o.getId());
        }
        model.addAttribute("list",list);
        model.addAttribute("au",au);
        return  "authorlist";
    }

    @RequestMapping("findByAuthorName2")
    public String findByAuthorName2(String name, Model model,HttpSession session){
        Author au = authorService.findByAuthorName(name);
        model.addAttribute("au",au);
        return  "authorzhuye";
    }

    @RequestMapping("toobook")
    public String toobook(int id,String name, Model model){
        Author au = authorService.findByAuthorName(name);

        return  "book";
    }

}
