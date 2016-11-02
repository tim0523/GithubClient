package com.yuyh.github.ui.repos;

import android.text.TextUtils;

import com.yuyh.github.api.content.CheckReadmeClient;
import com.yuyh.github.api.repo.RepoInfoClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.info.ReadmeInfo;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.utils.core.InfoUtils;
import com.yuyh.github.utils.LogUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class ReposPresenter extends RxPresenter implements ReposContract.Presenter {

    ReposContract.View view;

    public ReposPresenter(ReposContract.View view) {
        this.view = view;
    }

    @Override
    public void checkReadme(Repo repo) {
        Subscription subscription = new CheckReadmeClient(InfoUtils.createRepoInfo(repo))
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ReadmeInfo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.toString());
                        view.showCheckReadmeRet(false);
                    }

                    @Override
                    public void onNext(ReadmeInfo info) {
                        view.showCheckReadmeRet(info != null && !TextUtils.isEmpty(info.name));
                    }
                });
        addSubscrebe(subscription);
    }

    @Override
    public void requestRepo(Repo repo) {
        Subscription subscription = new RepoInfoClient(InfoUtils.createRepoInfo(repo))
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Repo>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Repo repo) {
                        LogUtils.i("repo-----" + repo.archive_url);
                        view.updateRepo(repo);
                    }
                });
        addSubscrebe(subscription);
    }
}
