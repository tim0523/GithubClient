package com.yuyh.github.api;

import com.yuyh.github.api.support.HeaderInterceptor;
import com.yuyh.github.api.support.LoggingInterceptor;
import com.yuyh.github.base.Constants;
import com.yuyh.github.bean.AccessToken;
import com.yuyh.github.bean.RequestToken;
import com.yuyh.github.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class GithubApi {

    private static GithubApi api;

    private GithubService service;

    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtils.i("oklog: " + message);
        }
    }

    public GithubApi() {
        LoggingInterceptor logging = new LoggingInterceptor(new MyLog());
        logging.setLevel(LoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true) // 失败重发
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(logging)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(client)
                .build();
        service = retrofit.create(GithubService.class);
    }

    public static GithubApi instance() {
        if (api == null) {
            api = new GithubApi();
        }
        return api;
    }

    public Observable<AccessToken> requestToken(String code, String clientId, String secret, String redirectUri) {
        RequestToken requestToken = new RequestToken(clientId, secret, code, redirectUri);
        return service.requestToken(requestToken);
    }
}
