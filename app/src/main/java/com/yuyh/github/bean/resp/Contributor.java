package com.yuyh.github.bean.resp;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Contributor {
    public User author;
    public int id;
    public int total;
    public List<Week> weeks;
}