package com.yuyh.github.support;

import android.text.TextUtils;

import com.yuyh.github.api.git.CommitInfoClient;
import com.yuyh.github.api.git.GitTreeInfoClient;
import com.yuyh.github.api.git.ReferenceInfoClient;
import com.yuyh.github.api.repo.RepoInfoClient;
import com.yuyh.github.bean.resp.GitCommit;
import com.yuyh.github.bean.resp.GitReference;
import com.yuyh.github.bean.resp.GitTree;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.utils.core.InfoUtils;
import com.yuyh.github.utils.core.RefUtils;

import java.io.IOException;

import rx.Observable;
import rx.Subscriber;

/**
 * Task to load the tree for a repository's default branch
 */
public class RefreshTreeTask implements Observable.OnSubscribe<FullTree> {

    private static final String TAG = "RefreshTreeTask";

    private final Repo repository;

    private final GitReference reference;

    /**
     * Create task to refresh repository's tree
     *
     * @param repository
     * @param reference
     */
    public RefreshTreeTask(final Repo repository,
                           final GitReference reference) {
        this.repository = repository;
        this.reference = reference;
    }

    private boolean isValidRef(GitReference ref) {
        return ref != null && ref.object != null
                && !TextUtils.isEmpty(ref.object.sha);
    }

    @Override
    public void call(Subscriber<? super FullTree> subscriber) {
        GitReference ref = reference;
        String branch = RefUtils.getPath(ref);
        if (branch == null) {
            branch = repository.default_branch;
            if (TextUtils.isEmpty(branch)) {
                branch = new RepoInfoClient(InfoUtils.createRepoInfo(repository))
                        .observable().toBlocking().first().default_branch;
                if (TextUtils.isEmpty(branch))
                    subscriber.onError(new IOException("Repo does not have master branch"));
            }
            branch = "refs/heads/" + branch;
        }

        if (!isValidRef(ref)) {
            ref = new ReferenceInfoClient(InfoUtils.createRepoInfo(repository, branch))
                    .observable()
                    .toBlocking()
                    .first();

            if (!isValidRef(ref))
                subscriber.onError(new IOException("Reference does not have associated commit SHA-1"));
        }

        GitCommit commit = new CommitInfoClient(InfoUtils.createRepoInfo(repository, ref.object.sha))
                .observable().toBlocking().first();
        if (commit == null || commit.tree == null
                || TextUtils.isEmpty(commit.tree.sha))
            subscriber.onError(new IOException("Commit does not have associated tree SHA-1"));

        GitTree tree = new GitTreeInfoClient(InfoUtils.createRepoInfo(repository, commit.tree.sha), true)
                .observable().toBlocking().first();
        subscriber.onNext(new FullTree(tree, ref));
    }
}