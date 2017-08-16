package com.three.zhongdian.user.entity;

import lombok.Data;

/**
 * Created by admin on 2017/8/5.
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String nickname;
    private String mail;
    private String phone;

}
