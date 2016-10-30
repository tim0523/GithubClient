package com.yuyh.github.ui.home.repos;

import android.content.Context;

import com.yuyh.easyadapter.recyclerview.EasyRVAdapter;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.github.R;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public class RepoListAdapter extends EasyRVAdapter<Repo> {

    public RepoListAdapter(Context context, List<Repo> list) {
        super(context, list, R.layout.item_home_repos);
    }

    @Override
    protected void onBindData(EasyRVHolder holder, int position, Repo item) {
        holder.setText(R.id.tvRepoUserName, item.owner.login)
                .setText(R.id.tvRepoName, item.name)
                .setText(R.id.tvRepoDesc, item.description)
                .setText(R.id.tvRepoStarCount, item.stargazers_count + "")
                .setText(R.id.tvRepoForkCount, item.forks_count + "")
                .setText(R.id.tvRepoLang, item.language);
    }
}
