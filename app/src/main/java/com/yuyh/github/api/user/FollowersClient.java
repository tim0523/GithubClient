package com.yuyh.github.api.user;

import android.text.TextUtils;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.resp.User;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class FollowersClient extends BaseClient<List<User>> {

    private String username;

    public FollowersClient() {
    }

    public FollowersClient(String username) {
        this.username = username;
    }


    @Override
    protected Observable<List<User>> getApiObservable(Retrofit retrofit) {
        if (TextUtils.isEmpty(username)) {
            return retrofit.create(UsersService.class).followers();
        } else {
            return retrofit.create(UsersService.class).followers(username);
        }
    }
}
