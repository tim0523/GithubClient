package com.yuyh.github.bean.resp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GistFile implements Serializable {
    public int size;
    public String content;
    public String type;
    public String filename;
    @SerializedName("raw_url")
    public String rawUrl;
    public boolean truncated;
    public String language;
}