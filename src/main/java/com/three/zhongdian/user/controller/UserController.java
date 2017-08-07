package com.three.zhongdian.user.controller;

import com.three.zhongdian.user.entity.User;
import com.three.zhongdian.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by admin on 2017/8/5.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("tologin")
    @ResponseBody
    public String login(HttpSession session,User user){
        User loginUser = userService.login(user);
        if(loginUser!=null) {
            session.setAttribute("loginUser", loginUser);
            return "1";
        }
        return "0";
    }
    /*
        清除session的方法
         */
    @RequestMapping("index")
    public String index(){return "index";}
    @RequestMapping("clearSession")
    public String clear(HttpSession session){
        System.out.println("开始登录"+"=============================");
        session.removeAttribute("loginUser");
        return "redirect:index";
    }

}
