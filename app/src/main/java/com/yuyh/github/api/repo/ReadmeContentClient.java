package com.yuyh.github.api.repo;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.RepoInfo;
import com.yuyh.github.bean.resp.Content;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class ReadmeContentClient extends BaseClient<Content> {

    private RepoInfo info;

    public ReadmeContentClient(RepoInfo info) {
        this.info = info;
    }

    @Override
    protected Observable<Content> getApiObservable(Retrofit retrofit) {

        Observable<Content> observable;

        if (info.branch == null) {
            observable = retrofit.create(RepoService.class).readme(info.owner, info.name);
        } else {
            observable = retrofit.create(RepoService.class).readme(info.owner, info.name, info.branch);
        }

        return observable;
    }
}
