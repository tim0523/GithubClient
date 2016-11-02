package com.yuyh.github.ui.repos.code;

import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.GitReference;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.support.FullTree;
import com.yuyh.github.support.RefreshTreeTask;
import com.yuyh.github.utils.LogUtils;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class CodePresenter extends RxPresenter implements CodeContract.Presenter {

    CodeContract.View view;

    public CodePresenter(CodeContract.View view) {
        this.view = view;
    }

    @Override
    public void refreshTree(Repo repo, GitReference reference) {
        Subscription subscription = Observable.create(new RefreshTreeTask(repo, reference))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FullTree>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("Exception loading tree:" + e.toString());
                    }

                    @Override
                    public void onNext(FullTree fullTree) {
                        view.renderFullTree(fullTree);
                    }
                });

        addSubscrebe(subscription);
    }
}
