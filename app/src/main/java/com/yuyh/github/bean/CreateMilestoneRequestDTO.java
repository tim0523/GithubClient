package com.yuyh.github.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.yuyh.github.bean.support.IssueState;

public class CreateMilestoneRequestDTO {
    public String title;
    public String description;
    public String due_on;
    public IssueState state;
}