package com.yuyh.github.ui.home.followers;

import com.yuyh.github.api.user.FollowersClient;
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
public class FollowersPresenter extends RxPresenter implements FollowersContract.Presenter {

    private FollowersContract.View view;

    public FollowersPresenter(FollowersContract.View view) {
        this.view = view;
    }

    @Override
    public void getMyFollowers() {
        Subscription subscription=new FollowersClient()
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
                        view.showMyFollowers(list);
                    }
                });
        addSubscrebe(subscription);
    }
}
