package com.yuyh.github.bean.resp;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class IssueEvent {
    public int id;
    public String url;
    public User actor;
    public String event;
    public String commit_id;
    public String created_at;
    public Label label;
    public Milestone milestone;
    public User assignee;
    public Rename rename;
}