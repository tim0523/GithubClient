package com.yuyh.github.ui.repos;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.bean.support.HomeItem;
import com.yuyh.github.ui.home.HomeAdapter;
import com.yuyh.github.ui.repos.code.CodeFragment;
import com.yuyh.github.ui.repos.commit.CommitFragment;
import com.yuyh.github.ui.repos.dynamics.DynamicsFragment;
import com.yuyh.github.ui.repos.readme.ReadmeFragment;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class ReposAdapter extends HomeAdapter {

    private boolean hasReadme;
    private Repo repo;

    private CodeFragment codeFragment;

    public ReposAdapter(Context context, List<HomeItem> names, FragmentManager fragmentManager,
                        boolean hasReadme, Repo repo) {
        super(context, names, fragmentManager);
        this.hasReadme = hasReadme;
        this.repo = repo;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        position = hasReadme ? position : position + 1;
        switch (position) {
            case 0:
                return ReadmeFragment.instance(repo);
            case 1:
                return DynamicsFragment.instance(repo);
            case 2:
                return (codeFragment = CodeFragment.instance(repo));
            case 3:
                return CommitFragment.instance(repo);
            default:
                return ReadmeFragment.instance(repo);
        }
    }

    /**
     * Pass back button pressed event down to fragments
     *
     * @return true if handled, false otherwise
     */
    public boolean onBackPressed() {
        return codeFragment != null && codeFragment.onBackPressed();
    }
}
