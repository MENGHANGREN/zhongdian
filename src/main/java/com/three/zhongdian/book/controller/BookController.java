package com.three.zhongdian.book.controller;


import com.three.zhongdian.book.po.BigType;
import com.three.zhongdian.book.po.Book;
import com.three.zhongdian.book.po.Tag;
import com.three.zhongdian.book.service.BookService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;


    @RequestMapping("/{url}")
    public String url(@PathVariable("url") String url) {
        return url;
    }

    @RequestMapping("/deleteTags")
    public ModelAndView deleteTags(HttpServletRequest request, String type, HttpSession session) {
        System.out.println(type);
        Map<String, Object> tags = (Map) session.getAttribute("tags");
        tags.remove(type);
        System.out.println(tags.size());

        session.setAttribute("tags", tags);
        return page(request, null, session);
        /*List<Book> books = bookService.findBookByMap(tags);
        List<BigType> bigTypes = bookService.findBigType();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books",books);
        mv.addObject("bigTypes",bigTypes);
        mv.addObject("tags",tags);
        return mv;*/
    }


    @RequestMapping("/findBookByStatus")
    public ModelAndView findBookByStatus(Integer currentPage, HttpServletRequest request, String words, Integer id, String type, String status, HttpSession session) {


        if ("type".equals(type)) {
            Map<String, Object> tags = (Map) session.getAttribute("tags");
            BigType bigType = bookService.findTypeById(id);
            Tag tag = new Tag();
            tag.setId(bigType.getId());
            tag.setName(bigType.getName());
            tags.put("type", tag);
            session.setAttribute("tags", tags);

        } else if ("status".equals(type)) {
            Map<String, Object> tags = (Map) session.getAttribute("tags");
            Tag tag = new Tag();
            tag.setName(status);
            tags.put("status", tag);

            session.setAttribute("tags", tags);

        } else if ("words".equals(type)) {
            Map<String, Object> tags = (Map) session.getAttribute("tags");
            String[] str = words.split(",");
            Tag tag = new Tag();
            if (Integer.parseInt(str[0]) == 300000) {
                tag.setName("30万-50万");
            } else if (Integer.parseInt(str[0]) == 0) {
                tag.setName("30万以下");
            } else if (Integer.parseInt(str[0]) == 500000) {
                tag.setName("50万-100万");
            } else if (Integer.parseInt(str[0]) == 1000000) {
                tag.setName("100万-200万");
            } else if (Integer.parseInt(str[0]) == 2000000) {
                tag.setName("200万以上");
            }

            tag.setMin(Integer.parseInt(str[0]));
            tag.setMax(Integer.parseInt(str[1]));
            tags.put("words", tag);
            session.setAttribute("tags", tags);

        } else if ("first".equals(type)) {
            Map map = new HashMap<String, Object>();
            session.setAttribute("tags", map);

        }
        return page(request, currentPage, session);
    }

    @RequestMapping("/searchBook")
    public ModelAndView searchBook(HttpSession session, String name) {
        Map<String, Object> tags = (Map) session.getAttribute("tags");
        tags.clear();
        session.setAttribute("tags", tags);
        List<Book> books = bookService.findBookByName(name);

        List<BigType> bigTypes = bookService.findBigType();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books", books);
        mv.addObject("bigTypes", bigTypes);
        mv.addObject("tags", tags);
        return mv;
    }

    @RequestMapping("/findBookById")
    public ModelAndView findBookById(int id) {
        Book book = bookService.findBookById(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("book");
        mv.addObject("book", book);
        return mv;
    }

    @RequestMapping(value = "/testDownload", method = RequestMethod.GET)
    public ResponseEntity<byte[]> download(@RequestParam("filepath") String filepath) throws Exception {
        String downloadFilePath = "D:\\book";//从我们的上传文件夹中去取

        File file = new File(downloadFilePath + File.separator + filepath);//新建一个文件

        HttpHeaders headers = new HttpHeaders();//http头信息

        String downloadFileName = new String(filepath.getBytes("UTF-8"), "iso-8859-1");//设置编码

        headers.setContentDispositionFormData("attachment", downloadFileName);

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //MediaType:互联网媒介类型  contentType：具体请求中的媒体类型信息

        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(new File(String.valueOf(file))),
                headers,
                HttpStatus.OK);
    }
    //在线阅读
    @RequestMapping("downLoad")
    public void downLoad(@RequestParam("filepath") String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            response.sendError(404, "File not found!");
            return;
        }
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;

        response.reset(); 
        if (isOnLine) { // 在线打开方式
            URL u = new URL("d://book" + filePath);
            response.setContentType(u.openConnection().getContentType());
            response.setHeader("Content-Disposition", "inline; filename=" + f.getName());
            // 文件名应该编码成UTF-8
        } else { // 纯下载方式
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0)
            out.write(buf, 0, len);
        br.close();
        out.close();
    }
    @RequestMapping("/page")
    public ModelAndView page(HttpServletRequest request, Integer currentPage, HttpSession session) {
        Map<String, Object> tags = (Map) session.getAttribute("tags");
        List<Book> books_size = bookService.findBookByMap(tags);
        int listCount = books_size.size();
        int pageSize = 6;
        if (currentPage == null) {
            currentPage = 0;
        }
        int pageCount = listCount / pageSize + (listCount % pageSize != 0 ? 1 : 0);

        String[] pageArray = new String[4];

        if (currentPage == 0) {
            pageArray[0] = "0";
        } else {
            pageArray[0] = "0";
        }

        if (currentPage == 0) {
            pageArray[1] = "0";
        } else {
            pageArray[1] = (currentPage - 1) + "";
        }

        if (currentPage < pageCount - 1) {
            pageArray[2] = (currentPage + 1) + "";
        } else {
            pageArray[2] = (pageCount - 1) + "";
        }

        if (currentPage < pageCount - 1) {
            pageArray[3] = (pageCount - 1) + "";
        } else {
            pageArray[3] = (pageCount - 1) + "";
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
        tag.setMin(currentPage * pageSize);
        tag.setMax(pageSize);
        tags.put("page", tag);
        List<Book> books = bookService.findBookByMap(tags);
        tags.remove("page");
        List<BigType> bigTypes = bookService.findBigType();

        session.setAttribute("tags", tags);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("list");
        mv.addObject("books", books);
        mv.addObject("bigTypes", bigTypes);
        mv.addObject("tags", tags);
        return mv;
    }
}
