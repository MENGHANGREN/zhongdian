package com.three.zhongdian.po;

public class Book implements java.io.Serializable {

    private Integer id;
    private String name;
    private Integer words;
    private String author;
    private Integer section;
    private Integer click;
    private String picpath;
    private String filepath;
    private String info;
    private Integer sid;
    private String status;

    private SmallType smallType;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", words=" + words +
                ", author='" + author + '\'' +
                ", section=" + section +
                ", click=" + click +
                ", picpath='" + picpath + '\'' +
                ", filepath='" + filepath + '\'' +
                ", info='" + info + '\'' +
                ", sid=" + sid +
                ", status='" + status + '\'' +
                ", smallType=" + smallType +
                '}';
    }

    public SmallType getSmallType() {
        return smallType;
    }

    public void setSmallType(SmallType smallType) {
        this.smallType = smallType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Integer getWords() {
        return words;
    }

    public void setWords(Integer words) {
        this.words = words;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getSection() {
        return section;
    }

    public void setSection(Integer section) {
        this.section = section;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }
}
