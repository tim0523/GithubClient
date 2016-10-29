package com.yuyh.github.bean.resp;

public class Label extends ShaUrl implements Comparable<Label> {

    public String name;
    public String color;

    public Label() {
    }

    @Override
    public int compareTo(Label another) {
        return name.toLowerCase().compareTo(another.name.toLowerCase());
    }
}