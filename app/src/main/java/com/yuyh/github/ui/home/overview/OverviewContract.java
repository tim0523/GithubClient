package com.yuyh.github.ui.home.overview;

import com.yuyh.github.base.BaseContract;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.User;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface OverviewContract {

    interface Presenter {

        void getMyInfo(int page);
    }

    interface View extends BaseContract.BaseView {

        void showMyInfo(User user);

        void showMyEvents(int page, List<GithubEvent> list);
    }

}
