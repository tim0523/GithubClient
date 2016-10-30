package com.yuyh.github.ui.home;

import com.yuyh.github.api.repo.Sort;
import com.yuyh.github.api.repo.StarListClient;
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
public class StarsPresenter extends RxPresenter implements StarsContract.Presenter {

    private StarsContract.View view;

    public StarsPresenter(StarsContract.View view) {
        this.view = view;
    }

    @Override
    public void getMyStars(final int page) {
        Subscription subscription = new StarListClient(1, Sort.PUSHED).observable()
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
                        view.showMyStars(page, list);
                    }
                });
        addSubscrebe(subscription);
    }
}
