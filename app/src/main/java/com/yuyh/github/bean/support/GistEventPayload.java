package com.yuyh.github.bean.support;

import com.yuyh.github.bean.resp.Gist;
import com.yuyh.github.bean.resp.Payload;

import java.io.Serializable;

public class GistEventPayload extends Payload implements Serializable {

    public Gist gist;
}