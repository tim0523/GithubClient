package com.yuyh.github.bean.resp;

import com.yuyh.github.bean.support.UserType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class Organization extends ShaUrl  implements Serializable {

    public int id;
    public String login;
    public String name;
    public String company;
    public Date created_at;
    public Date updated_at;
    public String avatar_url;
    public String gravatar_id;
    public String blog;
    public String bio;
    public String email;
    public String location;
    public UserType type;
    public boolean site_admin;
    public int public_repos;
    public int public_gists;
    public int followers;
    public int following;
}
