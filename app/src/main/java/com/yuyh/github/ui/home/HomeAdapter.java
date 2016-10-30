package com.yuyh.github.ui.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yuyh.github.R;
import com.yuyh.github.bean.support.HomeItem;
import com.yuyh.github.ui.home.followers.FollowersFragment;
import com.yuyh.github.ui.home.following.FollowingFragment;
import com.yuyh.github.ui.home.overview.OverviewFragment;
import com.yuyh.github.ui.home.repos.ReposFragment;
import com.yuyh.github.ui.home.stars.StarsFragment;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.widget.viewpager.indicator.IndicatorViewPager;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class HomeAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private LayoutInflater inflate;
    private List<HomeItem> names;

    public HomeAdapter(Context context, List<HomeItem> names, FragmentManager fragmentManager) {
        super(fragmentManager);
        inflate = LayoutInflater.from(context);
        this.names = names;
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        HomeItem item = names.get(position % names.size());
        if (convertView == null) {
            convertView = inflate.inflate(R.layout.tab_home_item, container, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.tvItemName);
        textView.setText(item.name);
        if (item.count != -1) {
            TextView count = (TextView) convertView.findViewById(R.id.tvItemCount);
            count.setText(item.count + "");
            count.setVisibility(View.VISIBLE);
        }
        int padding = ScreenUtils.dpToPxInt(15);
        convertView.setPadding(padding, 0, padding, 0);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new OverviewFragment();
                break;
            case 1:
                fragment = new ReposFragment();
                break;
            case 2:
                fragment = new StarsFragment();
                break;
            case 3:
                fragment = new FollowersFragment();
                break;
            case 4:
                fragment = new FollowingFragment();
                break;
            default:
                fragment = new ReposFragment();
                break;
        }
        return fragment;
    }
}
