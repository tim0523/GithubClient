package com.yuyh.github.bean;

import com.yuyh.github.bean.support.IssueState;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class IssueRequest {
    public String title;
    public String body;
    public String assignee;
    public Integer milestone;
    public String milestoneName;
    public String[] labels;
    public IssueState state;
}