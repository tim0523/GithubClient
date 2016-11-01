package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.security.Permissions;
import java.util.Date;
import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Repo implements Serializable {

    public boolean fork;
    @SerializedName("private")
    public boolean isPrivate;
    public Date created_at;
    public Date pushed_at;
    public Date updated_at;
    public int forks_count;
    public long id;
    public Repo parent;
    public Repo source;
    public String clone_url;
    public String description;
    public String homepage;
    public String git_url;
    public String language;
    public String default_branch;
    public String mirror_url;
    public String name;
    public String full_name;
    public String ssh_url;
    public String svn_url;
    public User owner;
    public int stargazers_count;
    public int subscribers_count;
    public int network_count;
    public int watchers_count;
    public int size;
    public int open_issues_count;
    public boolean has_issues;
    public boolean has_downloads;
    public boolean has_wiki;
    public Permissions permissions;
    public License license;
    public List<Branch> branches;
    public String archive_url;


}
