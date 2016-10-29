package com.yuyh.github.bean.resp;

import com.yuyh.github.bean.support.GitTreeType;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class GitTreeEntry extends ShaUrl {

    public String path;
    public String mode;
    public int size;
    public GitTreeType type;
}