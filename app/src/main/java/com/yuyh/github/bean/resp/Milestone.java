package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;
import com.yuyh.github.bean.support.MilestoneState;

public class Milestone extends ShaUrl implements Comparable<Milestone> {

    public String title;
    public int number;
    public MilestoneState state;
    public String description;
    public User creator;
    @SerializedName("open_issues")
    public int openIssues;
    @SerializedName("closes_issues")
    public int closedIssues;
    @SerializedName("created_at")
    public String createdAt;
    @SerializedName("updated_at")
    public String updatedAt;
    @SerializedName("due_on")
    public String dueOn;

    public Milestone() {
    }

    @Override
    public int compareTo(Milestone another) {
        return title.toLowerCase().compareTo(another.title.toLowerCase());
    }
}