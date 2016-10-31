package com.yuyh.github.bean.support;

import com.yuyh.github.bean.resp.Issue;

import java.io.Serializable;

public class IssueEventPayload extends ActionEventPayload implements Serializable {
    public Issue issue;
}
