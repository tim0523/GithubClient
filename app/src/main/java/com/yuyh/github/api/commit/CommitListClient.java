package com.yuyh.github.api.commit;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.CommitInfo;
import com.yuyh.github.bean.resp.Commit;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class CommitListClient extends BaseClient<List<Commit>> {

    private CommitInfo info;
    private String path;
    private int page;

    public CommitListClient(CommitInfo info, int page) {
        this.info = info;
        this.page = page;
    }

    public CommitListClient(CommitInfo info, String path, int page) {
        this.info = info;
        this.path = path;
        this.page = page;
    }

    @Override
    protected Observable<List<Commit>> getApiObservable(Retrofit retrofit) {
        CommitsService commitsService = retrofit.create(CommitsService.class);
        if (path == null) {
            if (info.sha == null) {
                if (page == 0) {
                    return commitsService.commits(info.repoInfo.owner, info.repoInfo.name);
                } else {
                    return commitsService.commits(info.repoInfo.owner, info.repoInfo.name, page);
                }
            } else {
                if (page == 0) {
                    return commitsService.commits(info.repoInfo.owner, info.repoInfo.name, info.sha);
                } else {
                    return commitsService.commits(info.repoInfo.owner, info.repoInfo.name, page, info.sha);
                }
            }
        } else {
            if (info.sha == null) {
                if (page == 0) {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path);
                } else {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, page);
                }
            } else {
                if (page == 0) {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, info.sha);
                } else {
                    return commitsService.commitsByPath(info.repoInfo.owner, info.repoInfo.name, path, info.sha,
                            page);
                }
            }
        }
    }
}
