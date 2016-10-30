package com.yuyh.github.ui.main;

import com.yuyh.github.base.BaseContract;
import com.yuyh.github.bean.resp.User;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public interface MainContract {

    interface Presenter extends BaseContract.BasePresenter {

        void requestToken(String code, String clientId, String secret, String redirectUri);

        void requestUserInfo();
    }

    interface View extends BaseContract.BaseView {

        void applyToken(String token);

        void showUserInfo(User user);
    }

}
