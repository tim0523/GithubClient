package com.yuyh.github.api.repo;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.RepoInfo;
import com.yuyh.github.bean.resp.Repo;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class RepoInfoClient extends BaseClient<Repo> {

    private RepoInfo info;

    public RepoInfoClient(RepoInfo info) {
        this.info = info;
    }

    @Override
    protected Observable<Repo> getApiObservable(Retrofit retrofit) {
        return retrofit.create(RepoService.class).get(info.owner, info.name);
    }
}
