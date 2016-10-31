package com.yuyh.github.api.events;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.resp.GithubEvent;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class UserEventsClient extends BaseClient<List<GithubEvent>> {

    private String username;
    private int page;

    public UserEventsClient(String username, int page) {
        this.username = username;
        this.page = page;
    }

    @Override
    protected Observable<List<GithubEvent>> getApiObservable(Retrofit retrofit) {
        if (page <= 0) {
            return retrofit.create(EventsService.class).events(username);
        } else {
            return retrofit.create(EventsService.class).events(username, page);
        }
    }
}
