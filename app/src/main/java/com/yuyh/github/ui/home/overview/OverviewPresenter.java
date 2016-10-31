package com.yuyh.github.ui.home.overview;

import com.yuyh.github.api.events.UserEventsClient;
import com.yuyh.github.api.user.UserInfoClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.manager.DataStorage;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
    public void getMyInfo(final int page) {
        Observable<List<GithubEvent>> observable;
        if (page == 1) {
            // If it's refreshing, first request the UserInfo, and then request Events
            observable = new UserInfoClient().observable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1<User, Observable<List<GithubEvent>>>() {
                        @Override
                        public Observable<List<GithubEvent>> call(User user) {
                            view.showMyInfo(user);
                            return new UserEventsClient(DataStorage.getInstance().getUserName(), page)
                                    .observable()
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread());
                        }
                    });
        } else {
            observable = new UserEventsClient(DataStorage.getInstance().getUserName(), page)
                    .observable()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        Subscription subscription = observable.subscribe(new Observer<List<GithubEvent>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<GithubEvent> events) {
                view.showMyEvents(page, events);
            }
        });
        addSubscrebe(subscription);
    }
}
