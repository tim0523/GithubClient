package com.yuyh.github.ui.repos.dynamics;

import com.yuyh.github.api.repo.RepoEventsClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.utils.core.InfoUtils;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class DynamicsPresenter extends RxPresenter implements DynamicsContract.Presenter {

    private DynamicsContract.View view;

    public DynamicsPresenter(DynamicsContract.View view) {
        this.view = view;
    }

    @Override
    public void requestRepoEvents(final int page, Repo repo) {
        Subscription subscription = new RepoEventsClient(page, InfoUtils.createRepoInfo(repo))
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GithubEvent>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<GithubEvent> events) {
                        view.showRepoEvents(page, events);
                    }
                });

        addSubscrebe(subscription);
    }
}
