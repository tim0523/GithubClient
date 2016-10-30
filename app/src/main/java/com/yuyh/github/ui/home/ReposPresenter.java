package com.yuyh.github.ui.home;

import com.yuyh.github.api.repo.RepoListClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class ReposPresenter extends RxPresenter implements ReposContract.Presenter {

    private ReposContract.View view;

    public ReposPresenter(ReposContract.View view) {
        this.view = view;
    }

    @Override
    public void getMyRepos() {
        Subscription subscription = new RepoListClient(null, RepoListClient.Sort.PUSHED).observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Repo> list) {
                        view.showMyRepos(list);
                    }
                });
        addSubscrebe(subscription);
    }
}
