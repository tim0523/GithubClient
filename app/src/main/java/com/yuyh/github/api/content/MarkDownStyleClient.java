package com.yuyh.github.api.content;

import com.yuyh.github.api.client.BaseClient;
import com.yuyh.github.bean.RequestMarkdown;

import retrofit2.Retrofit;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class MarkDownStyleClient extends BaseClient<String> {

    private RequestMarkdown md;

    public MarkDownStyleClient(RequestMarkdown md) {
        this.md = md;
    }

    @Override
    protected Observable<String> getApiObservable(Retrofit retrofit) {
        return retrofit.create(ContentService.class).markdown(md.text);
    }
}
