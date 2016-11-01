package com.yuyh.github.bean;

import java.io.Serializable;

public class RequestMarkdown implements Serializable {
    public String text;

    public RequestMarkdown(String text) {
        this.text = text;
    }
}