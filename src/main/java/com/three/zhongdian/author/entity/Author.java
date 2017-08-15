package com.three.zhongdian.author.entity;

import lombok.Data;

/**
 * 作者类
 * Created by admin on 2017/8/7.
 */
@Data
public class Author {
    private Integer id;
    private String name;//笔名
    private Integer productionCount;//作品数
    private Integer WordsCount;//累计字数
    private Integer writeDays;//创作天数
    private String grade;//作者等级
    private String authorintroduce;//作者介绍
    private String avatar;//作者头像
    private String sex;//作者性别
    private String provinces;//省份
    private Integer attention;//关注
    private Integer fans;//粉丝
    private String designation;//称号




}
