package com.yuyh.github.ui.repos;

import com.yuyh.github.bean.resp.Repo;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public interface ReposContract {

    interface Presenter {

        void checkReadme(Repo repo);

        void requestRepo(Repo repo);
    }

    interface View {

        void updateRepo(Repo repo);

        void showCheckReadmeRet(boolean hasReadme);
    }

}
