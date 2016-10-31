package com.yuyh.github.ui.home.overview;

import android.content.Context;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.github.R;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.support.GithubViewManager;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class EventsAdapter extends EasyRVAdapter<GithubEvent> {

    private GithubViewManager manager;

    public EventsAdapter(Context context, List<GithubEvent> list) {
        super(context, list, R.layout.item_home_events);
        manager = new GithubViewManager();
    }


    @Override
    protected void onBindData(EasyRVHolder holder, int position, GithubEvent event) {

        manager.updateEvent(holder, event);
    }
}
