package com.yuyh.github.ui.home;

import com.yuyh.github.api.repos.RepoListClient;
import com.yuyh.github.api.user.UserInfoClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.utils.LogUtils;
import com.yuyh.github.utils.ToastUtils;

import java.util.List;

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

        new RepoListClient("smuyyh", RepoListClient.Sort.FULLNAME).observable()
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
                        ToastUtils.showSingleToast(list.size() + list.get(0).archive_url);
                    }
                });
    }
}
