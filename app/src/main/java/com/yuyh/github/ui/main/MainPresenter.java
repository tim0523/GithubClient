package com.yuyh.github.ui.main;

import com.yuyh.github.api.login.RequestTokenClient;
import com.yuyh.github.api.user.UserInfoClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.AccessToken;
import com.yuyh.github.bean.RequestToken;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.manager.DataStorage;
import com.yuyh.github.utils.ToastUtils;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class MainPresenter extends RxPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void requestToken(String code, String clientId, String secret, String redirectUri) {
        RequestToken requestToken = new RequestToken(clientId, secret, code, redirectUri);

        Subscription subscription = new RequestTokenClient(requestToken).observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<AccessToken>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(AccessToken accessToken) {
                        ToastUtils.showSingleToast(accessToken.access_token);
                        DataStorage.getInstance().saveUserToken(accessToken.access_token);
                        view.applyToken(accessToken.access_token);
                    }
                });

        addSubscrebe(subscription);
    }

    @Override
    public void requestUserInfo() {
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
                        DataStorage.getInstance().saveUserInfo(user);
                        view.showUserInfo(user);
                    }
                });
        addSubscrebe(subscription);
    }
}
