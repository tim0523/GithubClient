package com.yuyh.github.bean.resp;

public class CommitComment extends GithubComment {
    public int position;
    public int line;
    public String commit_id;
    public String path;
}