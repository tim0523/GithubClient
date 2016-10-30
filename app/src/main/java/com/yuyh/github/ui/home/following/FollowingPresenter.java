package com.yuyh.github.ui.home.following;

import com.yuyh.github.api.user.FollowingClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.User;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class FollowingPresenter extends RxPresenter implements FollowingContract.Presenter {

    private FollowingContract.View view;

    public FollowingPresenter(FollowingContract.View view) {
        this.view = view;
    }

    @Override
    public void getMyFollowing(final int page) {
        Subscription subscription = new FollowingClient(page)
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<User> list) {
                        view.showMyFollowing(page, list);
                    }
                });
        addSubscrebe(subscription);
    }
}
