package com.three.zhongdian.author.entity;

import lombok.Data;

/**
 * Created by 任梦杭 on 2017/08/10.
 */
@Data
public class Author {
    private Integer aid;
    private String authorNname;
    private String newPassword;
    private String repeatPassword;
    private String email;
    private String QQ;
    private String realName;
    private String cardtype;
    private String cardId;
    private String tel_pre;
    private String province;
    private String address;
    private Integer productionCount;//作品数
    private Integer WordsCount;//累计字数
    private Integer writeDays;//创作天数
    private String grade;//作者等级
    private String authorintroduce;//作者介绍
    private String avatar;//作者头像
    private String sex;//作者性别
    private Integer attention;//关注
    private Integer fans;//粉丝
    private String designation;//称号
    private Integer uid;
}
