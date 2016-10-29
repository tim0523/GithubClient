package com.yuyh.github.bean.resp;

public class PullRequest extends Issue {

    public Head head;
    public Head base;
    public int additions;
    public int deletions;
    public int commits;
    public int changed_files;
    public boolean merged;
    public Boolean mergeable;
    public String patch_url;
    public String diff_url;
}