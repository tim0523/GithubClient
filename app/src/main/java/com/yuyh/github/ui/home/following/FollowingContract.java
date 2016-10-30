package com.yuyh.github.ui.home.following;

import com.yuyh.github.base.BaseContract;
import com.yuyh.github.bean.resp.User;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public interface FollowingContract {

    interface Presenter {
        void getMyFollowing(int page);
    }

    interface View extends BaseContract.BaseView {
        void showMyFollowing(int page, List<User> list);
    }
}
