package com.yuyh.github.api.client;

import android.text.TextUtils;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class ApiClientImpl implements ApiClient {

    private String hostname;

    public ApiClientImpl() {
    }

    public ApiClientImpl(String hostname) {
        if (!TextUtils.isEmpty(hostname)) {
            if (!hostname.startsWith("https://")) {
                hostname = "https://" + hostname;
            }
            this.hostname = hostname;
        }
    }

    @Override
    public String getApiOauthUrlEndpoint() {
        return hostname == null ? "https://github.com" : hostname;
    }

    @Override
    public String getApiEndpoint() {
        String hostname = "https://api.github.com";

        if (!TextUtils.isEmpty(this.hostname)) {
            hostname = this.hostname;
            if (!hostname.endsWith("/")) {
                hostname = hostname + "/";
            }

            hostname = hostname + "api/v3/";
        }

        return hostname;
    }

    @Override
    public String getType() {
        return "github";
    }
}
