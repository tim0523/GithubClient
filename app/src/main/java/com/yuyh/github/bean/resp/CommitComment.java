package com.yuyh.github.bean.resp;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class CommitComment extends GithubComment {
    public int position;
    public int line;
    public String commit_id;
    public String path;
}