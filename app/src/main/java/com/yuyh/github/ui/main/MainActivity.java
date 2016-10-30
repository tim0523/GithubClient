package com.yuyh.github.ui.main;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseAppCompatActivity;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.manager.DataStorage;
import com.yuyh.github.ui.auth.LoginActivity;
import com.yuyh.github.ui.home.HomeFragment;
import com.yuyh.github.utils.LogUtils;
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
    @Bind(R.id.content)
    LinearLayout content;

    private String clientId;
    private String secret;
    private String redirectUri;

    private MainPresenter mPresenter;

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

        mPresenter = new MainPresenter(this);

        initAuth();
    }

    private void initAuth() {
        String token = DataStorage.getInstance().getUserToken();
        if (TextUtils.isEmpty(token)) {
            // auth
            readyGoForResult(LoginActivity.class, REQ_AUTH_CODE);
        } else {
            applyToken(token);
        }
    }

    private void requestToken(Uri uri) {
        if (uri != null && uri.getScheme().equals(getString(R.string.github_oauth_scheme))) {
            String code = uri.getQueryParameter("code");
            LogUtils.i("code = " + code);

            clientId = getString(R.string.github_client);
            secret = getString(R.string.github_secret);
            redirectUri = getString(R.string.github_oauth);

            mPresenter.requestToken(code, clientId, secret, redirectUri);
        }
    }

    @Override
    public void applyToken(String token) {
        mPresenter.requestUserInfo();
    }

    @Override
    public void showUserInfo(User user) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        HomeFragment home = new HomeFragment();
        transaction.replace(R.id.content, home);
        transaction.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_AUTH_CODE && resultCode == RESULT_OK) {
            requestToken(data.getData());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }
}
