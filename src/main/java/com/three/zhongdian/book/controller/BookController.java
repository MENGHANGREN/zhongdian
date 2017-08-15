package com.three.zhongdian.book.controller;

import com.github.pagehelper.PageHelper;
import com.three.zhongdian.author.entity.Author;
import com.three.zhongdian.author.service.AuthorService;
import com.three.zhongdian.book.po.BigType;
import com.three.zhongdian.book.po.Book;
import com.three.zhongdian.book.po.Comment;
import com.three.zhongdian.book.po.Tag;
import com.three.zhongdian.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
public class BookController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;



     @RequestMapping("/{url}")
     public String url(@PathVariable("url") String url){return url;}
    @RequestMapping("/deleteTags")
    public ModelAndView deleteTags(HttpServletRequest request,String type,HttpSession session){
        System.out.println(type);
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        tags.remove(type);
        System.out.println(tags.size());

        session.setAttribute("tags",tags);
        return page(request,null,session);
    }


    @RequestMapping("/findBookByStatus")
    public ModelAndView findBookByStatus(Integer currentPage,HttpServletRequest request,String words,Integer id,String type,String status,HttpSession session){


        if("type".equals(type)){
            Map<String,Object> tags = (Map)session.getAttribute("tags");
            BigType bigType = bookService.findTypeById(id);
            Tag tag = new Tag();
            tag.setId(bigType.getId());
            tag.setName(bigType.getName());
            tags.put("type",tag);
            session.setAttribute("tags",tags);

        }
        else if("status".equals(type)){
            Map<String,Object> tags = (Map)session.getAttribute("tags");
            Tag tag = new Tag();
            tag.setName(status);
            tags.put("status",tag);

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
                tags.put("words",tag);
            session.setAttribute("tags",tags);

        }
        else if("first".equals(type)){
            Map map = new HashMap<String,Object>();
            session.setAttribute("tags",map);

        }else if("orders".equals(type)){
            Map<String,Object> tags = (Map)session.getAttribute("tags");
            Tag tag = new Tag();
            tag.setName(status);
            tags.put("orders",tag);
            session.setAttribute("tags",tags);
        }
        return  page(request,currentPage,session);
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
    public ModelAndView findBookById(int id,String name,HttpSession session){
 /*   @RequestMapping("/findBookById")
    public ModelAndView findBookById(int id){
        Book book = bookService.findBookById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("book");
        Author au = authorService.findByAuthorName(book.getAuthor());
        session.setAttribute("au",au);
        mv.addObject("book",book);
        return mv;
    }*/
    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res,String filepath) {
        System.out.println(filepath);
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        String name = UUID.randomUUID().toString();
        res.setHeader("Content-Disposition", "attachment;filename=" + name+".txt");
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

    @RequestMapping("/page")
    public ModelAndView page(HttpServletRequest request, Integer currentPage, HttpSession session){
        Map<String,Object> tags = (Map)session.getAttribute("tags");
        List<Book> books_size = bookService.findBookByMap(tags);
        int listCount = books_size.size();
        int pageSize = 6;
        if(currentPage==null){
            currentPage = 0;
        }
        int pageCount =  listCount / pageSize + (listCount % pageSize != 0 ? 1 : 0);

        String[] pageArray = new String[4];

        if (currentPage == 0) {
            pageArray[0] = "0";
        } else {
            pageArray[0] = "0";
        }

        if (currentPage == 0) {
            pageArray[1] = "0";
        } else {
            pageArray[1] = (currentPage-1)+"";
        }

        if (currentPage < pageCount - 1) {
            pageArray[2] = (currentPage+1)+"";
        } else {
            pageArray[2] = (pageCount-1)+"";
        }

        if (currentPage < pageCount - 1) {
            pageArray[3] = (pageCount-1)+"";
        } else {
            pageArray[3] = (pageCount-1)+"";
        }
        //首页
        request.setAttribute("firstPage", pageArray[0]);
        //上一页
        request.setAttribute("precursorPage", pageArray[1]);
        //下一页
        request.setAttribute("nextPage", pageArray[2]);
        //末页
        request.setAttribute("lastPage", pageArray[3]);
        //当前页
        request.setAttribute("currentPage", String.valueOf(currentPage + 1));
        //总页数
        request.setAttribute("pageCount", String.valueOf(pageCount));
        //总记录数
        request.setAttribute("listCount", listCount);
        //每一页显示记录
        request.setAttribute("pageSize", pageSize);


        Tag tag = new Tag();
        tag.setMin(currentPage*pageSize);
        tag.setMax(pageSize);
        tags.put("page",tag);
        List<Book> books = bookService.findBookByMap(tags);
        tags.remove("page");
        List<BigType> bigTypes = bookService.findBigType();

        session.setAttribute("tags",tags);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);
        return mv;
    }


    @RequestMapping("/saveComment")
    @ResponseBody
    public ModelAndView  saveComment(String content,Integer userid,Integer bookid){

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setBid(bookid);
        comment.setDate(new Date());
        comment.setUid(userid);
        bookService.insertComment(comment);
        return findBookById(bookid);
    }
    @RequestMapping("/findBookById")
    public ModelAndView findBookById(Integer id){
        List<Comment> comments = bookService.findComment(id);
        Book book = bookService.findBookById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("book");
        mv.addObject("book",book);
        mv.addObject("comments",comments);
        return mv;
    }
}
