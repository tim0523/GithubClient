package com.yuyh.github.ui.home.overview;

import com.yuyh.github.api.user.UserInfoClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.utils.LogUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class OverviewPresenter extends RxPresenter implements OverviewContract.Presenter {

    private OverviewContract.View view;

    public OverviewPresenter(OverviewContract.View view) {
        this.view = view;
    }


    @Override
    public void getMyInfo() {
        Subscription subscription = new UserInfoClient().observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(User user) {
                        LogUtils.i(user.toString());
                        view.showMyInfo(user);
                    }
                });
        addSubscrebe(subscription);
    }
}
