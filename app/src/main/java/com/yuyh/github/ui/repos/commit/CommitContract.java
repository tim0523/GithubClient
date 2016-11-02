package com.yuyh.github.ui.repos.commit;

import com.yuyh.github.bean.resp.Commit;
import com.yuyh.github.bean.resp.Repo;

import java.util.List;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public interface CommitContract {

    interface Presenter {

        void getCommitList(Repo repo, String last, int page);
    }

    interface View {

        void showCommitList(int page, List<Commit> list);
    }
}
