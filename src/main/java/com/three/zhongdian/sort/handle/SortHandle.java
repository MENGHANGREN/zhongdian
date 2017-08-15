package com.three.zhongdian.sort.handle;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.three.zhongdian.sort.pojo.Book;
import com.three.zhongdian.sort.service.SortBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by zhx on 2017/8/11.
 */

@Controller
public class SortHandle {

    @Autowired
    private SortBookService sortBookService;

    @RequestMapping("sortIndex")
    public String index(Map<String,Object> map) {

        List<Book> booksByTiket = sortBookService.getBooksByTiketTop10();
        Book firstBook = booksByTiket.remove(0);
        int snum =2;
        for (Book book : booksByTiket) {
            book.setSortNum(snum);
            snum++;
        }
        map.put("firstBook",firstBook);
        map.put("bbt",booksByTiket);
        return "sort/sortIndex";
    }

    @RequestMapping("newSort")
    public String newSort(Map<String, Object> map,@RequestParam(required=false,defaultValue="1") Integer pageNum,@RequestParam(required=false,defaultValue="20") Integer pageSize,@RequestParam(required=false) String typeId) {
        if(pageNum==0) {
            pageNum =1;
        }
        List<Book> books;
        PageHelper.startPage(pageNum,pageSize);
        if (typeId == null || typeId == "") {
            books = sortBookService.getBooksByTiket();
        } else {
            books = sortBookService.getBooksByType(Integer.parseInt(typeId));
        }
        
        int snum = 1;
        for (Book book : books) {
            book.setSortNum(snum);
            snum++;
        }
        PageInfo<Book> pages = new PageInfo<Book>(books);

        boolean hasNextPage = pages.isHasNextPage();
        if ( !hasNextPage) {
            pages.setNextPage(pages.getPages());
        }

        map.put("page",pages);
        map.put("books",books);
        map.put("typeId",typeId);
        return  "sort/newSort";
    }

}
