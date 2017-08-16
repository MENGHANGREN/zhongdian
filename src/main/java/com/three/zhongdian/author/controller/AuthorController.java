package com.three.zhongdian.author.controller;

import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.author.service.AuthorService;
import com.three.zhongdian.book.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by 任梦杭 on 2017/08/10.
 */
@Controller
public class AuthorController {
    @Autowired
    private AuthorService authorService;
    @RequestMapping("wlogin")
    public ModelAndView wlogin(HttpSession session){
        ModelAndView mv = new ModelAndView();
        ModelAndView mv2 = new ModelAndView();
        if(session.getAttribute("loginUser")!=null){
            System.out.println("已经登陆");
            mv.setViewName("author");
            return mv;
        }
        System.out.println("未登录");
        mv2.setViewName("wlogin");
        return mv;
    }
    @RequestMapping("yzAuthorLogin")
    @ResponseBody
    public  String yzAuthorLogin(HttpSession session){
        System.out.println("开始登陆");
        if(session.getAttribute("loginUser")!=null){
            System.out.println("登陆成功");
            return "1";
        }
        return "0";
    }
    @RequestMapping("addAuthor")
    public String addAuthor(Author author){
        System.out.println("添加作者");
        authorService.addAuthor(author);
        return "author";
    }
    @RequestMapping("findByAuthorName")
    public String findByAuthorName(String name, Model model, HttpSession session){
        Author au = authorService.findByAuthorName(name);
        List<Book> list = authorService.selectByAuthorName(name);
        for (Book o : list) {
            System.out.println(o.getId());
        }
        model.addAttribute("list",list);
        model.addAttribute("au",au);
        return  "authorlist";
    }

    @RequestMapping("selectByAuthorName")
    public String findByAuthorName2(String name, Model model){
        Author au = authorService.findByAuthorName(name);
        model.addAttribute("au",au);
        return  "authorzhuye";
    }

    @RequestMapping("toobook")
    public String toobook(String name, Model model){
        Author au = authorService.findByAuthorName(name);

        return  "book";
    }
}

