package com.yuyh.github.base;

import android.view.View;

import com.yuyh.github.bean.resp.Gist;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.Issue;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.support.EventMatcher;
import com.yuyh.github.support.OnRVItemClickListener;
import com.yuyh.github.ui.home.overview.EventsAdapter;
import com.yuyh.github.ui.repos.ReposActivity;

import java.util.ArrayList;

/**
 * @author yuyh.
 * @date 2016/11/2.
 */
public abstract class BaseEventFragment extends BaseListFragment implements OnRVItemClickListener<GithubEvent> {

    protected EventsAdapter mAdapter;
    protected EventMatcher mEventMatcher = new EventMatcher();

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setAdapter(mAdapter = new EventsAdapter(mActivity, new ArrayList<GithubEvent>()));
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(View view, int position, GithubEvent event) {
        switch (event.getType()) {
            case DownloadEvent:
                break;
            case PushEvent:
                break;
            case CommitCommentEvent:
                break;
            default:
                Repo repo = mEventMatcher.getRepository(event);
                if (repo != null) {
                    ReposActivity.start(mActivity, repo);
                    break;
                }

                Issue issue = mEventMatcher.getIssue(event);
                if (issue != null) {
                    break;
                }

                Gist gist = mEventMatcher.getGist(event);
                if (gist != null) {
                    break;
                }

                EventMatcher.UserPair userPair = mEventMatcher.getUsers(event);
                if (userPair != null) {
                    break;
                }
                break;
        }
    }
}
