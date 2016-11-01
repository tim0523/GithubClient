package com.yuyh.github.ui.repos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.bean.support.HomeItem;
import com.yuyh.github.ui.home.HomeAdapter;
import com.yuyh.github.ui.repos.readme.ReadmeFragment;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class ReposAdapter extends HomeAdapter {

    private boolean hasReadme;
    private Repo repo;

    public ReposAdapter(Context context, List<HomeItem> names, FragmentManager fragmentManager,
                        boolean hasReadme, Repo repo) {
        super(context, names, fragmentManager);
        this.hasReadme = hasReadme;
        this.repo = repo;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        Fragment fragment;
        position = hasReadme ? position : position + 1;
        switch (position) {
            case 0:
                fragment = ReadmeFragment.instance(repo);
                break;
            default:
                fragment = ReadmeFragment.instance(repo);
                break;
        }
        return fragment;
    }
}
