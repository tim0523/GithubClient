package com.yuyh.github.bean.resp;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
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