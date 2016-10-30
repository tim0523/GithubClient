package com.yuyh.github.ui.home.overview;

import com.yuyh.github.base.BaseContract;
import com.yuyh.github.bean.resp.User;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface OverviewContract {

    interface Presenter {

        void getMyInfo();
    }

    interface View extends BaseContract.BaseView {

        void showMyInfo(User user);
    }

}
