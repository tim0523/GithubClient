package com.yuyh.github.ui.repos.readme;

import com.yuyh.github.bean.resp.Repo;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public interface ReadmeContract {

    interface Presenter {

        void getReadme(Repo repo);
    }

    interface View {

        void showReadme(String content);
    }
}
