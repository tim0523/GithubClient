package com.yuyh.github.bean.resp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.yuyh.github.bean.support.ActionEventPayload;

import java.io.Serializable;

public class PullRequestEventPayload extends ActionEventPayload implements Serializable {
    public int number;
    public PullRequest pull_request;
    @SerializedName("public")
    public boolean is_public;
    public Organization org;
    public String created_at;
}