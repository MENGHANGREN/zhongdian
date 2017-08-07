package com.three.zhongdian.author.entity;

import lombok.Data;

/**
 * 作者类
 * Created by admin on 2017/8/7.
 */
@Data
public class Author {
    private Integer id;
    private String name;
    private Integer productionCount;
    private Integer WordsCount;
    private Integer writeDays;
    private String grade;
}
