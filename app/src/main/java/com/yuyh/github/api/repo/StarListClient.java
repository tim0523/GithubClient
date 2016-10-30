package com.yuyh.github.api.repo;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class StarListClient extends BaseClient<List<Repo>> {

    private int page;
    private String sort;

    public StarListClient(int page, @Sort String sort) {
        this.page = page;
        this.sort = sort;
    }

    @Override
    protected Observable<List<Repo>> getApiObservable(Retrofit retrofit) {
        if (page < 0) {
            return retrofit.create(RepoService.class).userStarredReposList(page, sort);
        } else {
            return retrofit.create(RepoService.class).userStarredReposList(sort);
        }
    }
}
