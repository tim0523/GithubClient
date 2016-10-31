package com.yuyh.github.bean.support;

import com.yuyh.github.bean.resp.Payload;
import com.yuyh.github.bean.resp.User;

import java.io.Serializable;

public class FollowEventPayload extends Payload implements Serializable {

    public User target;
}