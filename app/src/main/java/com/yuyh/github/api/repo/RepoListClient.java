package com.yuyh.github.api.repo;

import android.support.annotation.StringDef;
import android.text.TextUtils;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.resp.Repo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class RepoListClient extends BaseClient<List<Repo>> {

    @StringDef({
            Sort.FULLNAME,
            Sort.CREATED,
            Sort.UPDATED,
            Sort.PUSHED
    })

    @Retention(RetentionPolicy.SOURCE)
    public @interface Sort {
        String FULLNAME = "full_name";

        String CREATED = "created";

        String UPDATED = "updated";

        String PUSHED = "pushed";
    }

    private String username;

    private String sort;

    public RepoListClient(String username, @Sort String sort) {
        this.username = username;
        this.sort = sort;
    }

    @Override
    protected Observable<List<Repo>> getApiObservable(Retrofit retrofit) {
        if (TextUtils.isEmpty(username))
            return retrofit.create(RepoService.class).userReposList(sort);
        else
            return retrofit.create(RepoService.class).userReposList(username, sort);
    }
}
