package com.yuyh.github.base;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class RxPresenter implements BaseContract.BasePresenter {

    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    @Override
    public void detachView() {
        unSubscribe();
    }
}
