package com.yuyh.github.ui.home;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.widget.viewpager.indicator.IndicatorViewPager;
import com.yuyh.github.widget.viewpager.indicator.ScrollIndicatorView;
import com.yuyh.github.widget.viewpager.indicator.slidebar.ColorBar;
import com.yuyh.github.widget.viewpager.indicator.transition.OnTransitionTextListener;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class HomeFragment extends BaseLazyFragment {

    @Bind(R.id.home_indicator)
    ScrollIndicatorView scrollIndicatorView;
    @Bind(R.id.home_viewPager)
    ViewPager viewPager;

    private IndicatorViewPager indicatorViewPager;


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home);
        initView();
    }

    private void initView() {
        String[] names = getResources().getStringArray(R.array.home_tabs);

        scrollIndicatorView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        scrollIndicatorView.setScrollBar(new ColorBar(mActivity,
                ContextCompat.getColor(mActivity, R.color.tab_scrollbar), ScreenUtils.dpToPxInt(3)));
        scrollIndicatorView.setSplitAuto(true);
        scrollIndicatorView.setOnTransitionListener(new OnTransitionTextListener().setColor(ContextCompat.getColor(mActivity, R.color.tab_selected),
                ContextCompat.getColor(mActivity, R.color.tab_unselected)));

        viewPager.setOffscreenPageLimit(names.length);
        indicatorViewPager = new IndicatorViewPager(scrollIndicatorView, viewPager);
        indicatorViewPager.setAdapter(new HomeAdapter(mActivity, names, getChildFragmentManager()));
    }
}
