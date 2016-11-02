package com.yuyh.github.ui.repos.dynamics;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yuyh.github.base.BaseEventFragment;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class DynamicsFragment extends BaseEventFragment implements DynamicsContract.View {

    public final static String BUNDLE_REPO = "repo";

    public static Fragment instance(Repo repo) {
        Fragment fragment = new DynamicsFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_REPO, repo);
        fragment.setArguments(bundle);
        return fragment;
    }

    private Repo repo;

    private DynamicsPresenter mPresenter;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

        repo = (Repo) getArguments().getSerializable(BUNDLE_REPO);

        requestEventList(1);
    }

    private void requestEventList(int page) {
        showLoadding();

        if (mPresenter == null)
            mPresenter = new DynamicsPresenter(this);

        mPresenter.requestRepoEvents(page, repo);
    }

    @Override
    protected void onRefresh() {
        requestEventList(1);
    }

    @Override
    public void showRepoEvents(int page, List<GithubEvent> list) {
        if (page == 1) {
            mAdapter.clear();
        }
        if ((list == null || list.isEmpty()) && page == 1) {
            showEmptyView();
        } else {
            hideEmptyView();
        }
        mAdapter.addAll(list);

        mRefreshLayout.refreshComplete();
        hideLoadding();
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
