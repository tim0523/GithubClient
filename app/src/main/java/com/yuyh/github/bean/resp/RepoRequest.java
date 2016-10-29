package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class RepoRequest {

    public String name;
    public String description;
    public String homepage;
    @SerializedName("private")
    public boolean isPrivate;
    public boolean has_issues;
    public boolean has_wiki;
    public boolean has_downloads;
    public String default_branch;
    public boolean auto_init;
    public String gitignore_template;
    public String license_template;
    public int team_id;
}