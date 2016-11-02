package com.yuyh.github.ui.home.overview;

import android.content.Context;
import android.view.View;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.github.R;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.support.GithubViewManager;
import com.yuyh.github.support.OnRVItemClickListener;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class EventsAdapter extends EasyRVAdapter<GithubEvent> {

    private GithubViewManager manager;

    private OnRVItemClickListener<GithubEvent> listener;

    public EventsAdapter(Context context, List<GithubEvent> list) {
        super(context, list, R.layout.item_common_events);
        manager = new GithubViewManager();
    }

    @Override
    protected void onBindData(final EasyRVHolder holder, final int position, final GithubEvent event) {

        manager.updateEvent(holder, event);

        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getItemView(), position, event);
                }
            }
        });
    }

    public void setOnItemClickListener(OnRVItemClickListener listener) {
        this.listener = listener;
    }
}
