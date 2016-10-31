package com.yuyh.github.support;

import android.view.View;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public interface OnRVItemClickListener<T> {

    void onItemClick(View view, int position, T item);
}
