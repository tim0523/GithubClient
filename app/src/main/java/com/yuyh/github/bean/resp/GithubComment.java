package com.yuyh.github.bean.resp;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class GithubComment extends ShaUrl {

    private static final int MAX_MESSAGE_LENGHT = 146;
    public String id;
    public String body;
    public String body_html;
    public User user;
    public String created_at;
    public String updated_at;

    public GithubComment() {
    }

    public String shortMessage() {
        if (body != null) {
            if (body.length() > MAX_MESSAGE_LENGHT) {
                return body.substring(0, MAX_MESSAGE_LENGHT).concat("...");
            } else {
                return body;
            }
        }
        return null;
    }
}