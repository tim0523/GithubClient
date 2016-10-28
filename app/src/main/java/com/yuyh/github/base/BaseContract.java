package com.yuyh.github.base;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public interface BaseContract {

    interface BasePresenter<T> {
        void detachView();
    }

    interface BaseView {

    }

}
