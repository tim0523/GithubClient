package com.yuyh.github.bean;

import java.io.Serializable;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class RequestToken implements Serializable {

    public String client_id;
    public String client_secret;
    public String code;
    public String redirect_uri;

    public RequestToken(String client_id, String client_secret, String code, String redirect_uri) {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.code = code;
        this.redirect_uri = redirect_uri;
    }
}
