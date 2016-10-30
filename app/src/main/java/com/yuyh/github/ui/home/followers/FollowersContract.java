package com.yuyh.github.ui.home.followers;

import com.yuyh.github.base.BaseContract;
import com.yuyh.github.bean.resp.User;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public interface FollowersContract {

    interface Presenter {
        void getMyFollowers();
    }

    interface View extends BaseContract.BaseView {
        void showMyFollowers(List<User> list);
    }
}
