package com.yuyh.github.support;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.yuyh.github.bean.resp.Gist;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.Issue;
import com.yuyh.github.bean.resp.IssueCommentEventPayload;
import com.yuyh.github.bean.resp.PullRequestEventPayload;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.bean.support.EventType;
import com.yuyh.github.bean.support.FollowEventPayload;
import com.yuyh.github.bean.support.GistEventPayload;
import com.yuyh.github.bean.support.IssueEventPayload;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class EventMatcher {

    /**
     * Get issue from event
     *
     * @param event
     * @return issue or null if event doesn't apply
     */
    public Issue getIssue(GithubEvent event) {
        if (event == null)
            return null;
        if (event.payload == null)
            return null;

        Gson gson = new Gson();
        String json = gson.toJson(event.payload);

        switch (event.type) {
            case IssuesEvent:
                return gson.fromJson(json, IssueEventPayload.class).issue;
            case IssueCommentEvent:
                return gson.fromJson(json, IssueCommentEventPayload.class).issue;
            case PullRequestEvent:
                return gson.fromJson(json, PullRequestEventPayload.class).pull_request;
            default:
                return null;
        }
    }

    /**
     * Get gist from event
     *
     * @param event
     * @return gist or null if event doesn't apply
     */
    public Gist getGist(final GithubEvent event) {
        if (event == null)
            return null;
        if (event.payload == null)
            return null;

        Gson gson = new Gson();
        String json = gson.toJson(event.payload);

        EventType type = event.getType();
        if (EventType.GistEvent.equals(type))
            return (gson.fromJson(json, GistEventPayload.class)).gist;
        else
            return null;
    }

    /**
     * Get {@link Repo} from event
     *
     * @param event
     * @return gist or null if event doesn't apply
     */
    public Repo getRepository(final GithubEvent event) {
        if (event == null)
            return null;

        if (event.payload == null)
            return null;

        EventType type = event.getType();
        if (EventType.ForkEvent.equals(type)) {
            Repo repository = event.payload.forkee;
            // Verify repository has valid name and owner
            if (repository != null && !TextUtils.isEmpty(repository.name)
                    && repository.owner != null
                    && !TextUtils.isEmpty(repository.owner.login))
                return repository;
        }

        if (EventType.CreateEvent.equals(type) || EventType.WatchEvent.equals(type)
                || EventType.PublicEvent.equals(type))
            return eventRepoToRepo(event.repo);

        return null;
    }

    /**
     * Converts an event repo to a new, clean repo.
     *
     * @param repo The original repo.
     * @return A new repo, with a name and a new owner's login.
     */
    public static Repo eventRepoToRepo(Repo repo) {
        Repo newRepo = new Repo();
        String[] ref = repo.name.split("/");
        newRepo.owner = new User();
        newRepo.owner.login = ref[0];
        newRepo.name = ref[1];
        return newRepo;
    }

    /**
     * Get {@link UserPair} from event
     *
     * @param event
     * @return user or null if event doesn't apply
     */
    public UserPair getUsers(final GithubEvent event) {
        if (event == null)
            return null;

        if (event.payload == null)
            return null;

        Gson gson = new Gson();
        String json = gson.toJson(event.payload);

        EventType type = event.getType();
        if (EventType.FollowEvent.equals(type)) {
            User from = event.actor;
            User to = gson.fromJson(json, FollowEventPayload.class).target;
            if (from != null && to != null)
                return new UserPair(from, to);
        }

        return null;
    }

    /**
     * Pair of users in an {@link GithubEvent}
     */
    public static class UserPair {

        /**
         * Actor in event
         */
        public final User from;

        /**
         * User being acted upon
         */
        public final User to;

        private UserPair(final User from, final User to) {
            this.from = from;
            this.to = to;
        }
    }


}
