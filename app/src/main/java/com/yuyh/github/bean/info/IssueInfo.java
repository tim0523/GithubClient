package com.yuyh.github.bean.info;

import com.yuyh.github.bean.support.IssueState;

import java.io.Serializable;

public class IssueInfo implements Serializable {
    public RepoInfo repoInfo;
    public int num;
    public int commentNum;
    public IssueState state = IssueState.open;

    public IssueInfo() {
    }

    public IssueInfo(RepoInfo repoInfo) {
        this.repoInfo = repoInfo;
    }
}