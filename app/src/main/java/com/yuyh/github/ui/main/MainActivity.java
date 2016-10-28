package com.yuyh.github.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseAppCompatActivity;
import com.yuyh.github.manager.DataStorage;
import com.yuyh.github.ui.auth.LoginActivity;
import com.yuyh.github.utils.ToastUtils;
import com.yuyh.github.widget.guillotine.GuillotineAnimation;

import butterknife.Bind;

public class MainActivity extends BaseAppCompatActivity implements MainContract.View {

    public static final int REQ_AUTH_CODE = 1;

    @Bind(R.id.root)
    FrameLayout root;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.ivToolbarMenu)
    View ivToolbarMenu;

    private String clientId;
    private String secret;
    private String redirectUri;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);

        // add menu layout
        View menu = LayoutInflater.from(this).inflate(R.layout.layout_guillotine, null);
        View menuIcon = menu.findViewById(R.id.ivLayoutMenu);
        root.addView(menu);

        // init menu animation
        new GuillotineAnimation.Builder(menu, menuIcon, ivToolbarMenu)
                .setStartDelay(0)
                .setActionBarViewForAnimation(mToolbar)
                .setClosedOnStart(true)
                .build();

        initAuth();
    }

    private void initAuth() {
        if (TextUtils.isEmpty(DataStorage.getInstance().getUserToken())) {
            readyGoForResult(LoginActivity.class, REQ_AUTH_CODE);
        } else {

        }
    }

    private void requestToken(Uri uri) {
        if (uri != null && uri.getScheme().equals(getString(R.string.github_oauth_scheme))) {
            String code = uri.getQueryParameter("code");

            ToastUtils.showSingleToast(code);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_AUTH_CODE && resultCode == RESULT_OK) {
            requestToken(data.getData());
        }
    }
}
