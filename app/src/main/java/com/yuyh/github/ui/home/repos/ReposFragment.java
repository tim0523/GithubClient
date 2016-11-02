package com.yuyh.github.ui.home.repos;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.support.OnRVItemClickListener;
import com.yuyh.github.ui.repos.ReposActivity;
import com.yuyh.github.widget.DividerItemDecoration;
import com.yuyh.github.widget.ptr.PtrDefaultHandler;
import com.yuyh.github.widget.ptr.PtrFrameLayout;
import com.yuyh.github.widget.ptr.PtrHandler;
import com.yuyh.github.widget.ptr.StoreHouseHeader;
import com.yuyh.github.widget.ptr.utils.HeaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class ReposFragment extends BaseLazyFragment
        implements ReposContract.View, OnRVItemClickListener<Repo> {

    @Bind(R.id.refreshLayout)
    PtrFrameLayout mRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView rvRepos;

    private ReposPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private RepoListAdapter mAdapter;
    private List<Repo> mList;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_common_list);

        initView();

        getMyRepos();
    }

    private void initView() {
        StoreHouseHeader header = HeaderUtils.getPtrHeader(mActivity);
        mRefreshLayout.setDurationToCloseHeader(2000);
        mRefreshLayout.setHeaderView(header);
        mRefreshLayout.addPtrUIHandler(header);
        mRefreshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                getMyRepos();
            }
        });

        rvRepos.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(mActivity);
        rvRepos.setLayoutManager(mLayoutManager);
        rvRepos.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        mAdapter = new RepoListAdapter(mActivity, mList = new ArrayList<>());
        mAdapter.setOnItemClickListener(this);
        rvRepos.setAdapter(mAdapter);
    }

    public void getMyRepos() {
        showLoadding();
        if (mPresenter == null) {
            mPresenter = new ReposPresenter(this);
        }
        mPresenter.getMyRepos();
    }

    @Override
    public void showMyRepos(List<Repo> list) {
        mRefreshLayout.refreshComplete();
        mAdapter.clear();
        mAdapter.addAll(list);
        hideLoadding();
    }

    @Override
    public void onItemClick(View view, int position, Repo item) {
        ReposActivity.start(mActivity, item);
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
