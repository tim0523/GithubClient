package com.yuyh.github.ui.main;

import com.yuyh.github.base.BaseContract;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public interface MainContract {

    interface Presenter {

        void requestToken(String code, String clientId, String secret, String redirectUri);
    }

    interface View extends BaseContract.BaseView {
        void applyToken(String token);
    }

}
