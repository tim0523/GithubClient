package com.yuyh.github.ui.home;

import com.yuyh.github.base.BaseContract;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public interface StarsContract {

    interface Presenter {

        void getMyStars(int page);
    }

    interface View extends BaseContract.BaseView {

        void showMyStars(int page, List<Repo> list);
    }

}
