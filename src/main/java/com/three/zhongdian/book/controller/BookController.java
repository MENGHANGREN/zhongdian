package com.three.zhongdian.book.controller;

import com.github.pagehelper.PageHelper;
import com.three.zhongdian.book.po.BigType;
import com.three.zhongdian.book.po.Book;
import com.three.zhongdian.book.po.Tag;
import com.three.zhongdian.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

     @RequestMapping("/{url}")
     public String url(@PathVariable("url") String url){return url;}
    @RequestMapping("/deleteTags")
    public ModelAndView deleteTags(String type,HttpSession session){
        System.out.println(type);
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        tags.remove(type);
        Tag tag1 = new Tag();
        tag1.setMin(0);
        tag1.setMax(6);
        tags.put("page",tag1);
        System.out.println(tags.size());

        session.setAttribute("tags",tags);
        Tag tagmap = (Tag)tags.get("page");
        int min = tagmap.getMin();
        int max = tagmap.getMax();

        PageHelper.startPage(min,max);
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
    public ModelAndView findBookByStatus(String words,Integer id,String type,String status,HttpSession session){


        if("type".equals(type)){
            Map<String,Object> tags = (Map)session.getAttribute("tags");
            BigType bigType = bookService.findTypeById(id);
            Tag tag = new Tag();
            tag.setId(bigType.getId());
            tag.setName(bigType.getName());
            tags.put("type",tag);
                Tag tag1 = new Tag();
                tag1.setMin(1);
                tag1.setMax(6);
                tags.put("page",tag1);
            session.setAttribute("tags",tags);
        }
        else if("status".equals(type)){
            Map<String,Object> tags = (Map)session.getAttribute("tags");
            Tag tag = new Tag();
            tag.setName(status);
            tags.put("status",tag);
             Tag tag1 = new Tag();
                tag1.setMin(1);
                tag1.setMax(6);
                tags.put("page",tag1);
            session.setAttribute("tags",tags);

        }
        else if("words".equals(type)){
            Map<String,Object> tags = (Map)session.getAttribute("tags");
            String[] str = words.split(",");
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
                Tag tag1 = new Tag();
                tag1.setMin(1);
                tag1.setMax(6);
                tags.put("page",tag1);
                tags.put("words",tag);
            session.setAttribute("tags",tags);
        }
        else if("first".equals(type)){
            Map map = new HashMap<String,Object>();
            Tag tag = new Tag();
            tag.setMin(1);
            tag.setMax(6);
            map.put("page",tag);
            session.setAttribute("tags",map);
        }
        else if("page".equals(type)){
            Map<String,Object> tags = (Map)session.getAttribute("tags");
            if(status.equals("1")){
                Tag tagmap = (Tag)tags.get("page");
                Tag tag = new Tag();
                tag.setMin(tagmap.getMin()-1);
                tag.setMax(tagmap.getMax());
                tags.put("page",tag);
            }
            else if(status.equals("2")){
                Tag tagmap = (Tag)tags.get("page");
                Tag tag = new Tag();
                tag.setMin(tagmap.getMin()+1);
                tag.setMax(tagmap.getMax());
                tags.put("page",tag);
            }
            session.setAttribute("tags",tags);
        }

        Map<String,Object> tags = (Map)session.getAttribute("tags");
        Tag tagmap = (Tag)tags.get("page");
        int min = tagmap.getMin();
        int max = tagmap.getMax();
        PageHelper.startPage(min,max);
        List<Book> books = bookService.findBookByMap(tags);
        List<BigType> bigTypes = bookService.findBigType();


        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);
        return mv;
    }
    @RequestMapping("/searchBook")
    public ModelAndView searchBook(HttpSession session,String name){
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        tags.clear();
        session.setAttribute("tags",tags);
       List<Book> books =  bookService.findBookByName(name);

        List<BigType> bigTypes = bookService.findBigType();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);
        return mv;
    }
    @RequestMapping("/findBookById")
    public ModelAndView findBookById(int id){
        Book book = bookService.findBookById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("book");
        mv.addObject("book",book);
        return mv;
    }
    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res,String filepath) {
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + filepath);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File("d://book//"
                    + filepath)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("success");
    }

}
