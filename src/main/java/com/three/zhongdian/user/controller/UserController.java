package com.three.zhongdian.user.controller;

import com.three.zhongdian.user.entity.User;
import com.three.zhongdian.user.service.UserService;
import com.three.zhongdian.util.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

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
    @ResponseBody
    public String clear(HttpSession session){
        System.out.println("开始清除");
        session.removeAttribute("loginUser");
        return "";
    }
    @RequestMapping("toValidateCode")
    @ResponseBody
    public String toValidateCode(HttpServletResponse response,HttpSession session)throws Exception{
        ValidateCode vCode = new ValidateCode(160,40,5,150);
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        //禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        vCode.write(response.getOutputStream());
        System.out.println(vCode.getCode().toString());
        session.setAttribute("code",vCode.getCode().toString());
        return null;
    }


}
