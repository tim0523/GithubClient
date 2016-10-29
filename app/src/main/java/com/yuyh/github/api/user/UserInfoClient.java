package com.yuyh.github.api.user;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.resp.User;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class UserInfoClient extends BaseClient<User> {

    @Override
    protected Observable<User> getApiObservable(Retrofit retrofit) {
        return retrofit.create(UsersService.class).me();
    }
}
