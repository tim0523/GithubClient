package com.yuyh.github.ui.repos.commit;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseListFragment;
import com.yuyh.github.bean.resp.Commit;
import com.yuyh.github.bean.resp.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public class CommitFragment extends BaseListFragment implements CommitContract.View {

    public static final String BUNDLE_REPO = "repo";

    public static CommitFragment instance(Repo repo) {
        CommitFragment fragment = new CommitFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_REPO, repo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Bind(R.id.rl_branch)
    View mRlBranch;
    @Bind(R.id.tvBranchIcon)
    TextView mTvBranchIcon;
    @Bind(R.id.tvBranch)
    TextView mTvBranch;

    private Repo repo;
    private String last;

    private CommitPresenter mPresenter;
    private CommitListAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_commit_list;
    }

    @Override
    protected void initView() {
        super.initView();

        mAdapter = new CommitListAdapter(mActivity, new ArrayList<Commit>());
        mRecyclerView.setAdapter(mAdapter);

        repo = (Repo) getArguments().getSerializable(BUNDLE_REPO);

        requestCommitList(1);
    }

    private void requestCommitList(int page) {
        if (mPresenter == null)
            mPresenter = new CommitPresenter(this);

        mPresenter.getCommitList(repo, last, page);
    }

    @Override
    protected void onRefresh() {
        requestCommitList(1);
    }

    @Override
    public void showCommitList(int page, List<Commit> list) {
        if (page == 1) {
            mAdapter.clear();
        }

        mAdapter.addAll(list);

        hideLoadding();
        mRefreshLayout.refreshComplete();
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
