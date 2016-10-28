package com.yuyh.github.ui.main;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public interface MainContract {

    interface Presenter {

        void requestToken(String code, String clientId, String secret, String redirectUri);
    }

    interface View {

    }

}
