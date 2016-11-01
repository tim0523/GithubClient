package com.yuyh.github.ui.repos.readme;

import android.text.TextUtils;
import android.util.Base64;

import com.yuyh.github.api.content.MarkDownStyleClient;
import com.yuyh.github.api.repo.ReadmeContentClient;
import com.yuyh.github.base.RxPresenter;
import com.yuyh.github.bean.RequestMarkdown;
import com.yuyh.github.bean.resp.Content;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.utils.InfoUtils;
import com.yuyh.github.utils.LogUtils;

import java.nio.charset.Charset;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class ReadmePresenter extends RxPresenter implements ReadmeContract.Presenter {

    private ReadmeContract.View view;

    public ReadmePresenter(ReadmeContract.View view) {
        this.view = view;
    }

    @Override
    public void getReadme(Repo repo) {
        Subscription subscription = new ReadmeContentClient(InfoUtils.createRepoInfo(repo))
                .observable()
                .filter(new Func1<Content, Boolean>() {
                    @Override
                    public Boolean call(Content content) {
                        return content != null
                                && !TextUtils.isEmpty(content.content)
                                && TextUtils.equals("base64", content.encoding);
                    }
                })
                .map(new Func1<Content, String>() {
                    @Override
                    public String call(Content content) {
                        byte[] data = Base64.decode(content.content, Base64.DEFAULT);
                        return new String(data, Charset.forName("UTF-8"));
                    }
                })
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return !TextUtils.isEmpty(s);
                    }
                })
                .map(new Func1<String, RequestMarkdown>() {
                    @Override
                    public RequestMarkdown call(String s) {
                        return new RequestMarkdown(s);
                    }
                })
                .flatMap(new Func1<RequestMarkdown, Observable<String>>() {
                    @Override
                    public Observable<String> call(RequestMarkdown md) {
                        return new MarkDownStyleClient(md).observable();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e("readme:" + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        view.showReadme(s);
                    }
                });

        addSubscrebe(subscription);
    }
}
