package com.yuyh.github.bean.support;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class HomeItem {

    public String name;

    public int count = -1;

    public HomeItem(String name) {
        this.name = name;
    }

    public HomeItem(String name, int count) {
        this.name = name;
        this.count = count;
    }
}
