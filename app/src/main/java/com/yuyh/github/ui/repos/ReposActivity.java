package com.yuyh.github.ui.repos;

import android.content.Context;
import android.content.Intent;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseSwipeBackCompatActivity;
import com.yuyh.github.bean.resp.Repo;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class ReposActivity extends BaseSwipeBackCompatActivity {

    public static void start(Context context, Repo repo) {
        context.startActivity(new Intent(context, ReposActivity.class));
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_repos;
    }

    @Override
    protected void initViewsAndEvents() {

    }
}
