package com.yuyh.github.ui.home.repos;

import com.yuyh.github.base.BaseContract;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/30.
 */
public interface ReposContract {

    interface Presenter {

        void getMyRepos();
    }

    interface View extends BaseContract.BaseView {

        void showMyRepos(List<Repo> list);
    }

}
