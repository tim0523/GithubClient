package com.yuyh.github.api.content;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.info.ReadmeInfo;
import com.yuyh.github.bean.info.RepoInfo;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * Get's readme content
 *
 * @author yuyh.
 * @date 2016/11/1.
 */
public class CheckReadmeClient extends BaseClient<ReadmeInfo> {

    private RepoInfo info;

    public CheckReadmeClient(RepoInfo info) {
        this.info = info;
    }

    @Override
    protected Observable<ReadmeInfo> getApiObservable(Retrofit retrofit) {
        return retrofit.create(ContentService.class).checkReadme(info.owner, info.name);
    }
}
