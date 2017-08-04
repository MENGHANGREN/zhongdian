package com.three.zhongdian.controller;

import com.github.pagehelper.PageHelper;
import com.three.zhongdian.po.BigType;
import com.three.zhongdian.po.Book;
import com.three.zhongdian.po.Tag;
import com.three.zhongdian.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/list")
    public ModelAndView list(HttpSession session){
        Map map = new HashMap<String,Object>();
        session.setAttribute("tags",map);
        List<Book> books = bookService.findBookByMap(map);
        List<BigType> bigTypes = bookService.findBigType();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        return mv;
    }


    @RequestMapping("/findBook")
    public ModelAndView findBook(int id, HttpSession session){


        BigType bigType = bookService.findTypeById(id);
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        Tag tag = new Tag();
        tag.setId(bigType.getId());
        tag.setName(bigType.getName());
        tags.put("type",tag);
        session.setAttribute("tags",tags);
        List<Book> books = bookService.findBookByMap(tags);
        List<BigType> bigTypes = bookService.findBigType();
        System.out.println(bigTypes);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);
        return mv;
    }

    @RequestMapping("/deleteTags")
    public ModelAndView deleteTags(String type,HttpSession session){
        System.out.println(type);
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        tags.remove(type);
        System.out.println(tags.size());

        session.setAttribute("tags",tags);
        List<Book> books = bookService.findBookByMap(tags);
        List<BigType> bigTypes = bookService.findBigType();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);
        return mv;
    }


    @RequestMapping("/findBookByStatus")
    public ModelAndView findBookByStatus(String status,HttpSession session){
        System.out.println(status);
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        Tag tag = new Tag();
        tag.setName(status);
        tags.put("status",tag);
        session.setAttribute("tags",tags);
        List<Book> books = bookService.findBookByMap(tags);

        List<BigType> bigTypes = bookService.findBigType();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);
        return mv;
    }

    @RequestMapping("/findBookByWords")
    public ModelAndView findBookByWords(String words,HttpSession session){
        String[] str = words.split(",");
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        Tag tag = new Tag();
        if(Integer.parseInt(str[0])==300000){
            tag.setName("30万-50万");
        }
        else if(Integer.parseInt(str[0])==0){
            tag.setName("30万以下");
        }
        else if(Integer.parseInt(str[0])==500000){
            tag.setName("50万-100万");
        }
        else if(Integer.parseInt(str[0])==1000000){
            tag.setName("100万-200万");
        }
        else if(Integer.parseInt(str[0])==2000000){
            tag.setName("200万以上");
        }

        tag.setMin(Integer.parseInt(str[0]));
        tag.setMax(Integer.parseInt(str[1]));
        tags.put("words",tag);
        List<Book> books = bookService.findBookByMap(tags);
        List<BigType> bigTypes = bookService.findBigType();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);

        return mv;
    }
}
