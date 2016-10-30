package com.yuyh.github.ui.home;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.widget.MyDecoration;
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
public class StarsFragment extends BaseLazyFragment implements StarsContract.View {

    @Bind(R.id.frame)
    PtrFrameLayout frame;
    @Bind(R.id.rvRepos)
    RecyclerView rvRepos;

    private StarsPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private RepoListAdapter mAdapter;
    private List<Repo> mList;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_common_list);

        initView();

        getMyRepos(1);
    }

    private void initView() {
        StoreHouseHeader header = HeaderUtils.getPtrHeader(mActivity);
        frame.setDurationToCloseHeader(2000);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);
        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                getMyRepos(1);
            }
        });

        rvRepos.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(mActivity);
        rvRepos.setLayoutManager(mLayoutManager);
        rvRepos.addItemDecoration(new MyDecoration(mActivity, LinearLayoutManager.HORIZONTAL));
        mAdapter = new RepoListAdapter(mActivity, mList = new ArrayList<>());
        rvRepos.setAdapter(mAdapter);
    }

    public void getMyRepos(int page) {
        showLoadding();
        if (mPresenter == null) {
            mPresenter = new StarsPresenter(this);
        }
        mPresenter.getMyStars(page);
    }

    @Override
    public void showMyStars(int page, List<Repo> list) {
        frame.refreshComplete();
        if (page == 1)
            mAdapter.clear();
        mAdapter.addAll(list);
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
