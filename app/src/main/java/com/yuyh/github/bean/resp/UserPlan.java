package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class UserPlan {

    public long collaborators;
    @SerializedName("private_repos")
    public long privateRepos;
    public long space;
    public String name;
}
