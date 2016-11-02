package com.yuyh.github.ui.repos.code;

import com.yuyh.github.bean.resp.GitReference;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.support.FullTree;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public interface CodeContract {

    interface Presenter {

        void refreshTree(Repo repo, GitReference reference);
    }

    interface View {

        void renderFullTree(FullTree tree);
    }
}
