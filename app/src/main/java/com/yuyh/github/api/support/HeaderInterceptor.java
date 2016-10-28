package com.yuyh.github.api.support;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Retrofit2 Cookie拦截器。用于保存和设置Cookies
 *
 * @author yuyh.
 * @date 16/8/6.
 */
public final class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (original.url().toString().contains("book/")) {
            Request request = original.newBuilder()
                    .addHeader("User-Agent", "ZhuiShuShenQi/3.40[preload=false;locale=zh_CN;clientidbase=android-nvidia]")
                    .build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }
}
