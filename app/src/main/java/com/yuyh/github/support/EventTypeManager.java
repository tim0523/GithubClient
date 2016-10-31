package com.yuyh.github.support;

import com.google.gson.Gson;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.support.IssueEventPayload;
import com.yuyh.github.utils.StyledText;
import com.yuyh.github.utils.TypefaceUtils;

public enum EventTypeManager {
    CommitCommentEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatCommitComment(event, main, details);
            return TypefaceUtils.ICON_COMMENT;
        }
    },
    CreateEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatCreate(event, main, details);
            return TypefaceUtils.ICON_CREATE;
        }
    },
    DeleteEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatDelete(event, main, details);
            return TypefaceUtils.ICON_DELETE;
        }
    },
    DownloadEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatDownload(event, main, details);
            return TypefaceUtils.ICON_UPLOAD;
        }
    },
    FollowEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatFollow(event, main, details);
            return TypefaceUtils.ICON_FOLLOW;
        }
    },
    ForkEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatFork(event, main, details);
            return TypefaceUtils.ICON_FORK;
        }
    },
    GistEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatGist(event, main, details);
            return TypefaceUtils.ICON_GIST;
        }
    },
    GollumEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatWiki(event, main, details);
            return TypefaceUtils.ICON_WIKI;
        }
    },
    IssueCommentEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatIssueComment(event, main, details);
            return TypefaceUtils.ICON_ISSUE_COMMENT;
        }
    },
    IssuesEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatIssues(event, main, details);

            Gson gson = new Gson();
            String json = gson.toJson(event.payload);

            String action = gson.fromJson(json, IssueEventPayload.class).action;
            String icon = null;
            if (GithubViewManager.ISSUES_PAYLOAD_ACTION_OPENED.equals(action))
                icon = TypefaceUtils.ICON_ISSUE_OPEN;
            else if (GithubViewManager.ISSUES_PAYLOAD_ACTION_REOPENED.equals(action))
                icon = TypefaceUtils.ICON_ISSUE_REOPEN;
            else if (GithubViewManager.ISSUES_PAYLOAD_ACTION_CLOSED.equals(action))
                icon = TypefaceUtils.ICON_ISSUE_CLOSE;
            return icon;
        }
    },
    MemberEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatAddMember(event, main, details);
            return TypefaceUtils.ICON_ADD_MEMBER;
        }
    },
    PublicEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatPublic(event, main, details);
            return null;
        }
    },
    PullRequestEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatPullRequest(event, main, details);
            return TypefaceUtils.ICON_PULL_REQUEST;
        }
    },
    PullRequestReviewCommentEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatReviewComment(event, main, details);
            return TypefaceUtils.ICON_COMMENT;
        }
    },
    PushEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatPush(event, main, details);
            return TypefaceUtils.ICON_PUSH;
        }
    },
    TeamAddEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatTeamAdd(event, main, details);
            return TypefaceUtils.ICON_ADD_MEMBER;
        }
    },
    WatchEvent {
        @Override
        public String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details) {
            manager.formatWatch(event, main, details);
            return TypefaceUtils.ICON_STAR;
        }
    };

    public abstract String generateIconAndFormatStyledText(GithubViewManager manager, GithubEvent event, StyledText main, StyledText details);
}