package com.yuyh.github.ui.repos.dynamics;

import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public interface DynamicsContract {

    interface Presenter {

        void requestRepoEvents(int page, Repo repo);
    }

    interface View {

        void showRepoEvents(int page, List<GithubEvent> list);
    }
}
