package com.three.zhongdian.po;

import java.util.List;

public class SmallType {
    private Integer id;
    private String name;
    private Integer bid;

    private BigType bigType;

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public BigType getBigType() {
        return bigType;
    }

    public void setBigType(BigType bigType) {
        this.bigType = bigType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    @Override
    public String toString() {
        return "SmallType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bid=" + bid +
                ", bigType=" + bigType +
                ", books=" + books +
                '}';
    }
}
