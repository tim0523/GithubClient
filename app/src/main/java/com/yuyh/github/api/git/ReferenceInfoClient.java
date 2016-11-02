package com.yuyh.github.api.git;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.RepoInfo;
import com.yuyh.github.bean.resp.GitReference;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class ReferenceInfoClient extends BaseClient<GitReference> {

    private RepoInfo info;

    public ReferenceInfoClient(RepoInfo info) {
        this.info = info;
    }

    @Override
    protected Observable<GitReference> getApiObservable(Retrofit retrofit) {
        return retrofit.create(GitDataService.class)
                .repoReference(info.owner, info.name, info.branch);
    }
}
