package com.yuyh.github.base;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.yuyh.github.R;
import com.yuyh.github.widget.DividerItemDecoration;
import com.yuyh.github.widget.ptr.PtrDefaultHandler;
import com.yuyh.github.widget.ptr.PtrFrameLayout;
import com.yuyh.github.widget.ptr.PtrHandler;
import com.yuyh.github.widget.ptr.StoreHouseHeader;
import com.yuyh.github.widget.ptr.utils.HeaderUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public abstract class BaseListFragment extends BaseLazyFragment {

    @Bind(R.id.refreshLayout)
    protected PtrFrameLayout mRefreshLayout;
    @Bind(R.id.recyclerView)
    protected RecyclerView mRecyclerView;
    @Bind(R.id.arrowUp)
    protected View mArrowUp;

    protected LinearLayoutManager layoutManager;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(getLayoutId());
        initView();
    }

    protected void initView() {
        final StoreHouseHeader header = HeaderUtils.getPtrHeader(mActivity);
        mRefreshLayout.setDurationToCloseHeader(3000);
        mRefreshLayout.setHeaderView(header);
        mRefreshLayout.addPtrUIHandler(header);

        mRefreshLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                onRefresh();
            }
        });

        mRecyclerView.setLayoutManager(layoutManager = new LinearLayoutManager(mActivity));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, LinearLayoutManager.VERTICAL));
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (layoutManager.findFirstVisibleItemPosition() > 2) {
                    mArrowUp.setVisibility(View.VISIBLE);
                } else {
                    mArrowUp.setVisibility(View.GONE);
                }
            }
        });
    }

    protected int getLayoutId() {
        return R.layout.fragment_common_list;
    }

    @OnClick(R.id.arrowUp)
    protected void arrowUp() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    protected abstract void onRefresh();
}
