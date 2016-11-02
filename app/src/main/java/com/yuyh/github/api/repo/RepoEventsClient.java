package com.yuyh.github.api.repo;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.RepoInfo;
import com.yuyh.github.bean.resp.GithubEvent;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class RepoEventsClient extends BaseClient<List<GithubEvent>> {

    private int page;
    private RepoInfo info;

    public RepoEventsClient(RepoInfo info) {
        this(1, info);
    }

    public RepoEventsClient(int page, RepoInfo info) {
        this.page = page;
        this.info = info;
    }

    @Override
    protected Observable<List<GithubEvent>> getApiObservable(Retrofit retrofit) {
        if (page < 1) {
            return retrofit.create(RepoService.class).events(info.owner, info.name);
        } else {
            return retrofit.create(RepoService.class).events(info.owner, info.name, page);
        }
    }
}
