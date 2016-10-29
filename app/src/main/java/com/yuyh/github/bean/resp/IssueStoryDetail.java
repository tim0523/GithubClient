package com.yuyh.github.bean.resp;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface IssueStoryDetail {
    boolean isList();

    String getType();

    long createdAt();

    User user();
}