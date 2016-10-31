package com.yuyh.github.bean.resp;

import android.os.Parcel;
import android.os.Parcelable;

import com.yuyh.github.bean.support.ActionEventPayload;

import java.io.Serializable;

public class IssueCommentEventPayload extends ActionEventPayload implements Serializable {
    public Issue issue;
    public GithubComment comment;
}