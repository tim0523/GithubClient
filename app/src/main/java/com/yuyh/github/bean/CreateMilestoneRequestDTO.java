package com.yuyh.github.bean;

import com.yuyh.github.bean.support.IssueState;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class CreateMilestoneRequestDTO {
    public String title;
    public String description;
    public String due_on;
    public IssueState state;
}