package com.yuyh.github.ui.home.overview;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.github.R;
import com.yuyh.github.base.BaseEventFragment;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.utils.glide.GlideRoundTransform;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class OverviewFragment extends BaseEventFragment implements OverviewContract.View {

    static class HeaderViewHolder {

        @Bind(R.id.overview_user_avatar)
        ImageView ivAvatar;
        @Bind(R.id.overview_user_name)
        TextView tvUsername;
        @Bind(R.id.overview_user_account)
        TextView tvAccount;
        @Bind(R.id.overview_user_company)
        TextView tvCompany;
        @Bind(R.id.overview_location)
        TextView tvLocation;
        @Bind(R.id.overview_email)
        TextView tvEmail;
        @Bind(R.id.overview_blog)
        TextView tvBlog;
        @Bind(R.id.overview_bio)
        TextView tvBio;

        public HeaderViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

        public void unBind() {
            ButterKnife.unbind(this);
        }
    }

    private HeaderViewHolder holder;
    private OverviewPresenter mPresenter;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);

        requestEvents(1);
    }

    @Override
    protected void onRefresh() {
        requestEvents(1);
    }

    private void requestEvents(int page) {
        showLoadding();

        if (mPresenter == null)
            mPresenter = new OverviewPresenter(this);

        mPresenter.getMyInfo(page);
    }

    @Override
    public void showMyInfo(User user) {

        if (holder == null) {
            View rvHeader = mAdapter.setHeaderView(R.layout.header_home_overview);
            holder = new HeaderViewHolder(rvHeader);
        }

        Glide.with(mActivity).load(user.avatar_url)
                .placeholder(new ColorDrawable(Color.GRAY))
                .transform(new GlideRoundTransform(mActivity))
                .into(holder.ivAvatar);

        holder.tvUsername.setText(user.name);
        holder.tvAccount.setText(user.login);

        if (!TextUtils.isEmpty(user.company)) {
            holder.tvCompany.setText(user.company);
            holder.tvCompany.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user.location)) {
            holder.tvLocation.setText(user.location);
            holder.tvLocation.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user.email)) {
            holder.tvEmail.setText(user.email);
            holder.tvEmail.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user.blog)) {
            holder.tvBlog.setText(user.blog);
            holder.tvBlog.setVisibility(View.VISIBLE);
        }

        holder.tvBio.setText(user.bio);
    }

    @Override
    public void showMyEvents(int page, List<GithubEvent> list) {
        if (page == 1) {
            mAdapter.clear();
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

        if (holder != null) {
            holder.unBind();
        }
    }
}
