package com.yuyh.github.ui.home.repos;

import android.content.Context;
import android.view.View;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.github.R;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.support.OnRVItemClickListener;
import com.yuyh.github.utils.FormatUtils;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class RepoListAdapter extends EasyRVAdapter<Repo> {

    private OnRVItemClickListener<Repo> listener;

    public RepoListAdapter(Context context, List<Repo> list) {
        super(context, list, R.layout.item_home_repos);
    }

    @Override
    protected void onBindData(final EasyRVHolder holder, final int position, final Repo item) {
        holder.setText(R.id.tvRepoUserName, item.owner.login)
                .setText(R.id.tvRepoName, item.name)
                .setText(R.id.tvRepoDesc, item.description)
                .setText(R.id.tvRepoStarCount, FormatUtils.formatTosepara(item.stargazers_count))
                .setText(R.id.tvRepoForkCount, FormatUtils.formatTosepara(item.forks_count))
                .setText(R.id.tvRepoLang, item.language);

        holder.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(holder.getItemView(), position, item);
                }
            }
        });
    }

    public void setOnItemClickListener(OnRVItemClickListener listener) {
        this.listener = listener;
    }
}
