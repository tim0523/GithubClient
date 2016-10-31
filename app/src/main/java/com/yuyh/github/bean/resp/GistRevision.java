package com.yuyh.github.bean.resp;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;

public class GistRevision extends ShaUrl implements Serializable {
    public Date committedAt;
    public GitChangeStatus changeStatus;
    public String version;
    public User user;
}