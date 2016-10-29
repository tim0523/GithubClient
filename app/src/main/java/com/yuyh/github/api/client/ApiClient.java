package com.yuyh.github.api.client;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface ApiClient {

    String getApiOauthUrlEndpoint();

    String getApiEndpoint();

    String getType();
}