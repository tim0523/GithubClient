package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;
import com.yuyh.github.bean.support.IssueState;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Issue extends GithubComment {

    public int number;
    public IssueState state;
    public boolean locked;
    public String title;
    public List<Label> labels;
    public User assignee;
    public Milestone milestone;
    public int comments;
    @SerializedName("pull_request")
    public PullRequest pullRequest;
    @SerializedName("closed_at")
    public String closedAt;
    public Repo repository;
}