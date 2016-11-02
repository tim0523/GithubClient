package com.yuyh.github.ui.repos.commit;

import com.yuyh.github.api.commit.CommitListClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.resp.Commit;
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
public class CommitPresenter extends RxPresenter implements CommitContract.Presenter {

    private CommitContract.View view;

    public CommitPresenter(CommitContract.View view) {
        this.view = view;
    }

    @Override
    public void getCommitList(Repo repo, String last, final int page) {
        Subscription subscription = new CommitListClient(InfoUtils.createCommitInfo(repo, last), page)
                .observable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Commit>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Commit> commits) {
                        view.showCommitList(page, commits);
                    }
                });

        addSubscrebe(subscription);
    }
}
