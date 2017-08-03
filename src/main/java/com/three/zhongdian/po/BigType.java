package com.three.zhongdian.po;

import java.util.List;

public class BigType {
    private Integer id;
    private String name;

    private List<SmallType> smallTypes;

    public List<SmallType> getSmallTypes() {
        return smallTypes;
    }

    public void setSmallTypes(List<SmallType> smallTypes) {
        this.smallTypes = smallTypes;
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

    @Override
    public String toString() {
        return "BigType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", smallTypes=" + smallTypes +
                '}';
    }

}
