package com.yuyh.github.api.git;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.RepoInfo;
import com.yuyh.github.bean.resp.GitCommit;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class CommitInfoClient extends BaseClient<GitCommit> {

    private RepoInfo info;

    public CommitInfoClient(RepoInfo info) {
        this.info = info;
    }

    @Override
    protected Observable<GitCommit> getApiObservable(Retrofit retrofit) {
        return retrofit.create(GitDataService.class)
                .repoCommit(info.owner, info.name, info.branch);
    }
}
