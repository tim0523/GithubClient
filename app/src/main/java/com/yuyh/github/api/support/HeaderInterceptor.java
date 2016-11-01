package com.yuyh.github.api.support;

import android.text.TextUtils;

import com.yuyh.github.manager.DataStorage;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Header Interceptor
 *
 * @author yuyh.
 * @date 16/8/6.
 */
public final class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder builder = original.newBuilder();
        if (!TextUtils.isEmpty(DataStorage.getInstance().getUserToken())) {
            builder.addHeader("Authorization", "token " + DataStorage.getInstance().getUserToken());
        }
        if(original.url().toString().contains("/markdown/raw")){
            builder.addHeader("Content-type", "text/plain");
        }
        if (original.url().toString().contains("/login/oauth/access_token")) {
            builder.addHeader("Accept", "application/json");
        } else {
            builder.addHeader("Accept", "application/vnd.github.v3.json")
                    .addHeader("User-Agent", "Gitskarios");
        }
        Request request = builder.build();
        return chain.proceed(request);
    }
}
