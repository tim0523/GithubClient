package com.yuyh.github.ui.repos;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseSwipeBackCompatActivity;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.bean.support.HomeItem;
import com.yuyh.github.utils.core.RepositoryUtils;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.widget.viewpager.indicator.IndicatorViewPager;
import com.yuyh.github.widget.viewpager.indicator.ScrollIndicatorView;
import com.yuyh.github.widget.viewpager.indicator.slidebar.ColorBar;
import com.yuyh.github.widget.viewpager.indicator.transition.OnTransitionTextListener;
import com.yuyh.github.widget.viewpager.viewpager.SViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class ReposActivity extends BaseSwipeBackCompatActivity implements ReposContract.View {

    public static final String PARA_REPO = "repo";
    private ReposAdapter mAdapter;

    public static void start(Context context, Repo repo) {
        context.startActivity(new Intent(context, ReposActivity.class)
                .putExtra(PARA_REPO, repo));
    }

    @Bind(R.id.indicator)
    ScrollIndicatorView mIndicatorView;
    @Bind(R.id.viewPager)
    SViewPager mViewPager;

    private IndicatorViewPager mIndicatorViewPager;

    private Repo repo;
    private User owner;
    private boolean hasReadme = false;

    private ReposPresenter mPresenter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_repos;
    }

    @Override
    protected void initViewsAndEvents() {
        showLoadding();

        mViewPager.setEnableScroll(false);

        repo = (Repo) getIntent().getSerializableExtra(PARA_REPO);
        owner = repo.owner;

        getSupportActionBar().setTitle(repo.name);
        getSupportActionBar().setSubtitle(owner.login);

        mPresenter = new ReposPresenter(this);

        if (!TextUtils.isEmpty(owner.avatar_url) && RepositoryUtils.isComplete(repo)) {
            checkReadme();
        } else {
            requestRepoInfo();
        }
    }

    private void checkReadme() {
        mPresenter.checkReadme(repo);
    }

    private void requestRepoInfo() {
        mPresenter.requestRepo(repo);
    }

    @Override
    public void updateRepo(Repo repo) {
        this.repo = repo;
        owner = repo.owner;
        checkReadme();
    }

    @Override
    public void showCheckReadmeRet(boolean hasReadme) {
        this.hasReadme = hasReadme;
        hideLoadding();
        initTabView();
    }

    private void initTabView() {
        String[] names = getResources().getStringArray(R.array.repo_tabs);
        List<HomeItem> list = new ArrayList<>();
        if (hasReadme) {
            list.add(new HomeItem("README"));
        }
        list.add(new HomeItem(names[0]));
        list.add(new HomeItem(names[1]));
        list.add(new HomeItem(names[2]));
        if (repo.has_issues) {
            list.add(new HomeItem(names[3], repo.open_issues_count));
        }

        mIndicatorView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mIndicatorView.setScrollBar(new ColorBar(mContext,
                ContextCompat.getColor(mContext, R.color.tab_scrollbar), ScreenUtils.dpToPxInt(3)));
        mIndicatorView.setSplitAuto(true);
        mIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(ContextCompat.getColor(mContext, R.color.tab_selected),
                ContextCompat.getColor(mContext, R.color.tab_unselected)));

        mViewPager.setOffscreenPageLimit(names.length);

        mIndicatorViewPager = new IndicatorViewPager(mIndicatorView, mViewPager);
        mIndicatorViewPager.setAdapter(mAdapter = new ReposAdapter(mContext, list, getSupportFragmentManager(), hasReadme, repo));
    }

    @Override
    public void onBackPressed() {
        if (!mAdapter.onBackPressed())
            super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }
}
