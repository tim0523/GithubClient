package com.yuyh.github.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseAppCompatActivity;
import com.yuyh.github.widget.guillotine.GuillotineAnimation;

import butterknife.Bind;

public class MainActivity extends BaseAppCompatActivity {

    @Bind(R.id.root)
    FrameLayout root;
    @Bind(R.id.ivToolbarMenu)
    View ivToolbarMenu;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {

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
    }
}
