package com.yuyh.github.api.login;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.AccessToken;
import com.yuyh.github.bean.RequestToken;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class RequestTokenClient extends BaseClient<AccessToken> {

    private RequestToken requestToken;

    public RequestTokenClient(RequestToken requestToken) {
        super("https://github.com");
        this.requestToken = requestToken;
    }

    @Override
    protected Observable<AccessToken> getApiObservable(Retrofit retrofit) {
        return retrofit.create(LoginService.class).requestToken(requestToken);
    }
}
