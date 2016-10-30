package com.yuyh.github.ui.home;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.utils.LogUtils;
import com.yuyh.github.utils.glide.GlideRoundTransform;
import com.yuyh.github.widget.ptr.PtrFrameLayout;
import com.yuyh.github.widget.ptr.PtrHandler;
import com.yuyh.github.widget.ptr.StoreHouseHeader;
import com.yuyh.github.widget.ptr.utils.HeaderUtils;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class OverviewFragment extends BaseLazyFragment implements OverviewContract.View {

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

    @Bind(R.id.frame)
    PtrFrameLayout frame;

    private OverviewPresenter mPresenter;

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home_overview);

        initView();

        mPresenter = new OverviewPresenter(this);
        mPresenter.getMyInfo();
    }

    private void initView() {
        final StoreHouseHeader header = HeaderUtils.getPtrHeader(mActivity);
        frame.setDurationToCloseHeader(3000);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);

        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return true;
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                LogUtils.e("refresh");
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void showMyInfo(User user) {
        Glide.with(mActivity).load(user.avatar_url)
                .placeholder(new ColorDrawable(Color.GRAY))
                .transform(new GlideRoundTransform(mActivity))
                .into(ivAvatar);

        tvUsername.setText(user.name);
        tvAccount.setText(user.login);

        if (!TextUtils.isEmpty(user.company)) {
            tvCompany.setText(user.company);
            tvCompany.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user.location)) {
            tvLocation.setText(user.location);
            tvLocation.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user.email)) {
            tvEmail.setText(user.email);
            tvEmail.setVisibility(View.VISIBLE);
        }

        if (!TextUtils.isEmpty(user.blog)) {
            tvBlog.setText(user.blog);
            tvBlog.setVisibility(View.VISIBLE);
        }

        tvBio.setText(user.bio);
    }
}
