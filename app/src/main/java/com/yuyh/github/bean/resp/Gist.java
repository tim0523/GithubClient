package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Gist extends ShaUrl implements Serializable {
    @SerializedName("public")
    public boolean isPublic;
    public String created_at;
    public String updated_at;
    public int comments;
    public List<GistRevision> history;
    public GistFilesMap files;
    public String description;
    @SerializedName("git_pull_url")
    public String gitPullUrl;
    @SerializedName("git_push_url")
    public String gitPushUrl;
    @SerializedName("forks_url")
    public String forksUrl;
    public String id;
    public User owner;
    public User user;
}