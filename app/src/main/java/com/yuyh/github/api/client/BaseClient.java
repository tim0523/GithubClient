package com.yuyh.github.api.client;

import com.yuyh.github.api.support.HeaderInterceptor;
import com.yuyh.github.api.support.LoggingInterceptor;
import com.yuyh.github.utils.LogUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public abstract class BaseClient<T> {

    protected static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20 * 1000, TimeUnit.MILLISECONDS)
            .readTimeout(20 * 1000, TimeUnit.MILLISECONDS)
            .retryOnConnectionFailure(true) // 失败重发
            .addInterceptor(new HeaderInterceptor())
            .addInterceptor(new LoggingInterceptor(new MyLog())
                    .setLevel(LoggingInterceptor.Level.BODY))
            .build();

    protected Retrofit retrofit;

    public BaseClient() {
        this(null);
    }

    public BaseClient(String hostname) {
        retrofit = new Retrofit.Builder()
                .baseUrl(new ApiClientImpl(hostname).getApiEndpoint())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 添加Rx适配器
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .client(client)
                .build();
    }

    public Observable<T> observable() {
        return getApiObservable(retrofit);
    }

    protected abstract Observable<T> getApiObservable(Retrofit retrofit);

    public static class MyLog implements LoggingInterceptor.Logger {
        @Override
        public void log(String message) {
            LogUtils.i("oklog: " + message);
        }
    }
}
