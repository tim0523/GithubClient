package com.yuyh.github.bean;

import com.yuyh.github.GithubApp;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class GithubAuthorization {
    public int id;
    public String url;
    public String[] scopes;
    public String token;
    public String token_last_eight;
    public String hashed_token;
    public GithubApp app;
    public String note;
    public String note_url;
    public String updated_at;
    public String created_at;
    public String fingerprint;

}
