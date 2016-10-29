package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Payload {
    public String action;
    public Repo repository;
    public User sender;
    public int number;
    public PullRequest pull_request;
    @SerializedName("public")
    public boolean is_public;
    public Organization org;
    public String created_at;
    public Issue issue;
    public CommitComment comment;
    public Release release;
    public Team team;
    public long push_id;
    public int size;
    public int distinct_size;
    public String ref_type;
    public String ref;
    public String head;
    public String before;
    public List<Commit> commits;
    public Repo forkee;
}
