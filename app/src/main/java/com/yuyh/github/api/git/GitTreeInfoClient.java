package com.yuyh.github.api.git;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.RepoInfo;
import com.yuyh.github.bean.resp.GitTree;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class GitTreeInfoClient extends BaseClient<GitTree> {

    private RepoInfo info;
    private boolean recursive;

    public GitTreeInfoClient(RepoInfo info, boolean recursive) {
        this.info = info;
        this.recursive = recursive;
    }

    @Override
    protected Observable<GitTree> getApiObservable(Retrofit retrofit) {
        if (recursive) {
            return retrofit.create(GitDataService.class).repoTreeRecursive(info.owner, info.name, info.branch);
        } else {
            return retrofit.create(GitDataService.class).repoTree(info.owner, info.name, info.branch);
        }
    }
}
