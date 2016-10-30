package com.yuyh.github.bean.resp;

import java.io.Serializable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class User extends Organization implements Serializable{

    public boolean hireable;
    public String date;
    public String followers_url;
    public String following_url;
    public String gists_url;
    public String starred_url;
    public String subscriptions_url;
    public String organizations_url;
    public String repos_url;
    public String events_url;
    public String received_events_url;
    public int private_gists;
    public int owned_public_repos;
    public int owned_private_repos;
    public int total_public_repos;
    public int total_private_repos;
    public int collaborators;
    public int disk_usage;
    public UserPlan plan;
    public int organizations;

    @Override
    public String toString() {
        return "User{" +
                "hireable=" + hireable +
                ", date='" + date + '\'' +
                ", followers_url='" + followers_url + '\'' +
                ", following_url='" + following_url + '\'' +
                ", gists_url='" + gists_url + '\'' +
                ", starred_url='" + starred_url + '\'' +
                ", subscriptions_url='" + subscriptions_url + '\'' +
                ", organizations_url='" + organizations_url + '\'' +
                ", repos_url='" + repos_url + '\'' +
                ", events_url='" + events_url + '\'' +
                ", received_events_url='" + received_events_url + '\'' +
                ", private_gists=" + private_gists +
                ", owned_public_repos=" + owned_public_repos +
                ", owned_private_repos=" + owned_private_repos +
                ", total_public_repos=" + total_public_repos +
                ", total_private_repos=" + total_private_repos +
                ", collaborators=" + collaborators +
                ", disk_usage=" + disk_usage +
                ", plan=" + plan +
                ", organizations=" + organizations +
                '}';
    }
}
