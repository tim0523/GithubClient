package com.yuyh.github.ui.home.followers;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.User;
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
 * @date 2016/10/30.
 */
public class FollowersFragment extends BaseLazyFragment implements FollowersContract.View {

    @Bind(R.id.refreshLayout)
    PtrFrameLayout mRefreshLaout;
    @Bind(R.id.recyclerView)
    RecyclerView mRvFollowers;

    private FollowersPresenter mPresenter;
    private FollowersAdapter mAdapter;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_common_list);
        initView();
    }

    private void initView() {
        StoreHouseHeader header = HeaderUtils.getPtrHeader(mActivity);
        mRefreshLaout.setDurationToCloseHeader(2000);
        mRefreshLaout.setHeaderView(header);
        mRefreshLaout.addPtrUIHandler(header);
        mRefreshLaout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                getFollowersList(1);
            }
        });

        mRvFollowers.setItemAnimator(new DefaultItemAnimator());
        mRvFollowers.setLayoutManager(new LinearLayoutManager(mActivity));
        mRvFollowers.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        if (mAdapter == null) {
            mAdapter = new FollowersAdapter(mActivity, new ArrayList<User>());
        }
        mRvFollowers.setAdapter(mAdapter);

        getFollowersList(1);
    }

    private void getFollowersList(int page) {
        showLoadding();
        if (mPresenter == null) {
            mPresenter = new FollowersPresenter(this);
        }
        mPresenter.getMyFollowers();
    }

    @Override
    public void showMyFollowers(List<User> list) {
        mAdapter.clear();
        mAdapter.addAll(list);
        hideLoadding();
    }
}
