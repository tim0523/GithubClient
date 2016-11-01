package com.yuyh.github.bean.resp;

import java.io.Serializable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Branch implements Serializable{

    public String name;
    public Commit commit;
    public Links _links;
}
