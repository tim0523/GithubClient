package com.yuyh.github.ui.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.bean.support.HomeItem;
import com.yuyh.github.manager.DataStorage;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.widget.viewpager.indicator.IndicatorViewPager;
import com.yuyh.github.widget.viewpager.indicator.ScrollIndicatorView;
import com.yuyh.github.widget.viewpager.indicator.slidebar.ColorBar;
import com.yuyh.github.widget.viewpager.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class HomeFragment extends BaseLazyFragment {

    @Bind(R.id.indicator)
    ScrollIndicatorView mIndicatorView;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private IndicatorViewPager mIndicatorViewPager;


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.common_indicator_layout);
        initView();
    }

    private void initView() {
        String[] names = getResources().getStringArray(R.array.home_tabs);
        User user = DataStorage.getInstance().getUserInfo();
        List<HomeItem> list = new ArrayList<>();
        list.add(new HomeItem(names[0]));
        list.add(new HomeItem(names[1], user.public_repos));
        list.add(new HomeItem(names[2]));
        list.add(new HomeItem(names[3], user.followers));
        list.add(new HomeItem(names[4], user.following));

        mIndicatorView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        mIndicatorView.setScrollBar(new ColorBar(mActivity,
                ContextCompat.getColor(mActivity, R.color.tab_scrollbar), ScreenUtils.dpToPxInt(3)));
        mIndicatorView.setSplitAuto(true);
        mIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(ContextCompat.getColor(mActivity, R.color.tab_selected),
                ContextCompat.getColor(mActivity, R.color.tab_unselected)));

        mViewPager.setOffscreenPageLimit(names.length);

        mIndicatorViewPager = new IndicatorViewPager(mIndicatorView, mViewPager);
        mIndicatorViewPager.setAdapter(new HomeAdapter(mActivity, list, getChildFragmentManager()));
    }
}
