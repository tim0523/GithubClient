package com.yuyh.github.ui.home;

import android.os.Bundle;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class HomeFragment extends BaseLazyFragment {

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_home);
    }
}
