package com.yuyh.github.bean.resp;

import com.google.gson.annotations.SerializedName;
import com.yuyh.github.bean.support.EventType;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class GithubEvent {
    public long id;
    public EventType type = EventType.Unhandled;
    public String name;
    public User actor;
    public User org;
    public Repo repo;
    public Payload payload;
    @SerializedName("public")
    public boolean public_event;
    public String created_at;

}
