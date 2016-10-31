package com.yuyh.github.support;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.easyadapter.recyclerview.EasyRVHolder;
import com.yuyh.github.GithubApp;
import com.yuyh.github.R;
import com.yuyh.github.bean.resp.Commit;
import com.yuyh.github.bean.resp.CommitComment;
import com.yuyh.github.bean.resp.GithubComment;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.Issue;
import com.yuyh.github.bean.resp.PullRequest;
import com.yuyh.github.bean.resp.Release;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.bean.resp.Team;
import com.yuyh.github.bean.resp.User;
import com.yuyh.github.bean.support.FollowEventPayload;
import com.yuyh.github.bean.support.GistEventPayload;
import com.yuyh.github.utils.IssueUtils;
import com.yuyh.github.utils.ScreenUtils;
import com.yuyh.github.utils.StyledText;
import com.yuyh.github.utils.TimeUtils;
import com.yuyh.github.utils.TypefaceUtils;
import com.yuyh.github.utils.glide.GlideRoundTransform;

import java.text.NumberFormat;
import java.util.List;

/**
 * @author yuyh.
 * @date 2016/10/31.
 */
public class GithubViewManager {

    public static final String ISSUES_PAYLOAD_ACTION_OPENED = "opened";
    public static final String ISSUES_PAYLOAD_ACTION_REOPENED = "reopened";
    public static final String ISSUES_PAYLOAD_ACTION_CLOSED = "closed";

    /**
     * update view for event list view
     *
     * @param holder event list item ViewHolder
     * @param event
     */
    public void updateEvent(EasyRVHolder holder, GithubEvent event) {

        Glide.with(GithubApp.getsInstance()).load(event.actor.avatar_url)
                .placeholder(R.mipmap.ic_default_avatar)
                .transform(new GlideRoundTransform(GithubApp.getsInstance()))
                .override(ScreenUtils.dpToPxInt(40), ScreenUtils.dpToPxInt(40))
                .into((ImageView) holder.getView(R.id.ivAvatar));

        StyledText main = new StyledText();
        StyledText details = new StyledText();

        String icon = EventTypeManager.valueOf(event.type.toString())
                .generateIconAndFormatStyledText(this, event, main, details);

        if (TextUtils.isEmpty(icon)) {
            holder.setVisible(R.id.tvEventIcon, View.GONE);
        } else {
            TypefaceUtils.setOcticons((TextView) holder.getView(R.id.tvEventIcon));
            holder.setText(R.id.tvEventIcon, icon);
        }

        ((TextView) holder.getView(R.id.tvEvent)).setText(main);

        if (TextUtils.isEmpty(details)) {
            holder.setVisible(R.id.tvEventDetails, View.GONE);
        } else {
            ((TextView) holder.getView(R.id.tvEventDetails)).setText(details);
        }

        ((TextView) holder.getView(R.id.tvEventDate)).setText(TimeUtils.getRelativeTime(event.created_at));

    }

    void formatCommitComment(GithubEvent event, StyledText main,
                             StyledText details) {
        boldActor(main, event);
        main.append(" commented on ");
        boldRepo(main, event);

        appendCommitComment(details, event.payload.comment);
    }

    void formatDownload(GithubEvent event, StyledText main,
                        StyledText details) {
        boldActor(main, event);
        main.append(" uploaded a file to ");
        boldRepo(main, event);

        Release download = event.payload.release;
        if (download != null)
            appendText(details, download.name);
    }

    void formatCreate(GithubEvent event, StyledText main,
                      StyledText details) {
        boldActor(main, event);

        main.append(" created ");
        String refType = event.payload.ref_type;
        main.append(refType);
        main.append(' ');
        if (!"repository".equals(refType)) {
            main.append(event.payload.ref);
            main.append(" at ");
            boldRepo(main, event);
        } else
            boldRepoName(main, event);
    }

    void formatDelete(GithubEvent event, StyledText main,
                      StyledText details) {
        boldActor(main, event);

        main.append(" deleted ");
        main.append(event.payload.ref_type);
        main.append(' ');
        main.append(event.payload.ref);
        main.append(" at ");

        boldRepo(main, event);
    }

    void formatFollow(GithubEvent event, StyledText main,
                      StyledText details) {
        boldActor(main, event);
        main.append(" started following ");
        boldUser(main, ((FollowEventPayload) event.payload).target);
    }

    void formatFork(GithubEvent event, StyledText main,
                    StyledText details) {
        boldActor(main, event);
        main.append(" forked repository ");
        boldRepo(main, event);
    }

    void formatGist(GithubEvent event, StyledText main,
                    StyledText details) {
        boldActor(main, event);

        GistEventPayload payload = (GistEventPayload) event.payload;

        main.append(' ');
        String action = payload.action;
        if ("create".equals(action))
            main.append("created");
        else if ("update".equals(action))
            main.append("updated");
        else
            main.append(action);
        main.append(" Gist ");
        main.append(payload.gist.id);
    }

    void formatWiki(GithubEvent event, StyledText main,
                    StyledText details) {
        boldActor(main, event);
        main.append(" updated the wiki in ");
        boldRepo(main, event);
    }

    void formatIssueComment(GithubEvent event, StyledText main,
                            StyledText details) {
        boldActor(main, event);

        main.append(" commented on ");

        Issue issue = event.payload.issue;
        String number;
        if (IssueUtils.isPullRequest(issue))
            number = "pull request " + issue.number;
        else
            number = "issue " + issue.number;
        main.bold(number);

        main.append(" on ");

        boldRepo(main, event);

        appendComment(details, event.payload.comment);
    }

    void formatIssues(GithubEvent event, StyledText main,
                      StyledText details) {
        boldActor(main, event);

        String action = event.payload.action;
        Issue issue = event.payload.issue;
        main.append(' ');
        main.append(action);
        main.append(' ');
        main.bold("issue " + issue.number);
        main.append(" on ");

        boldRepo(main, event);

        appendText(details, issue.title);
    }

    void formatAddMember(GithubEvent event, StyledText main,
                         StyledText details) {
        boldActor(main, event);
        main.append(" added ");
        main.bold(event.payload.sender.login);
        main.append(" as a collaborator to ");
        boldRepo(main, event);
    }

    void formatPublic(GithubEvent event, StyledText main,
                      StyledText details) {
        boldActor(main, event);
        main.append(" open sourced repository ");
        boldRepo(main, event);
    }

    void formatWatch(GithubEvent event, StyledText main,
                     StyledText details) {
        boldActor(main, event);
        main.append(" starred ");
        boldRepo(main, event);
    }

    void formatReviewComment(GithubEvent event, StyledText main,
                             StyledText details) {
        boldActor(main, event);
        main.append(" commented on ");
        boldRepo(main, event);

        appendCommitComment(details, event.payload.comment);
    }

    void formatPullRequest(GithubEvent event, StyledText main,
                           StyledText details) {
        boldActor(main, event);

        String action = event.payload.action;
        if ("synchronize".equals(action))
            action = "updated";
        main.append(' ');
        main.append(action);
        main.append(' ');
        main.bold("pull request " + event.payload.number);
        main.append(" on ");

        boldRepo(main, event);

        if (ISSUES_PAYLOAD_ACTION_OPENED.equals(action) || "closed".equals(action)) {
            PullRequest request = event.payload.pull_request;
            if (request != null) {
                String title = request.title;
                if (!TextUtils.isEmpty(title))
                    details.append(title);
            }
        }
    }

    void formatPush(GithubEvent event, StyledText main,
                    StyledText details) {
        boldActor(main, event);

        main.append(" pushed to ");
        String ref = event.payload.ref;
        if (ref.startsWith("refs/heads/"))
            ref = ref.substring(11);
        main.bold(ref);
        main.append(" at ");

        boldRepo(main, event);

        final List<Commit> commits = event.payload.commits;
        int size = commits != null ? commits.size() : -1;
        if (size > 0) {
            if (size != 1)
                details.append(NumberFormat.getIntegerInstance().format(size)).append(" new commits");
            else
                details.append("1 new commit");

            int max = 3;
            int appended = 0;
            for (Commit commit : commits) {
                if (commit == null)
                    continue;

                String sha = commit.sha;
                if (TextUtils.isEmpty(sha))
                    continue;

                details.append('\n');
                if (sha.length() > 7)
                    details.monospace(sha.substring(0, 7));
                else
                    details.monospace(sha);

                String message = commit.message;
                if (!TextUtils.isEmpty(message)) {
                    details.append(' ');
                    int newline = message.indexOf('\n');
                    if (newline > 0)
                        details.append(message.subSequence(0, newline));
                    else
                        details.append(message);
                }

                appended++;
                if (appended == max)
                    break;
            }
        }
    }

    void formatTeamAdd(GithubEvent event, StyledText main, StyledText details) {
        boldActor(main, event);

        main.append(" added ");

        Repo repo = event.payload.repository;
        String repoName = repo != null ? repo.name : null;
        if (repoName != null)
            main.bold(repoName);

        main.append(" to team");

        Team team = event.payload.team;
        String teamName = team != null ? team.name : null;
        if (teamName != null)
            main.append(' ').bold(teamName);
    }

    private void appendComment(final StyledText details, final GithubComment comment) {
        if (comment != null)
            appendText(details, comment.body);
    }

    private void appendCommitComment(final StyledText details, final CommitComment comment) {
        if (comment == null)
            return;

        String id = comment.commit_id;
        if (!TextUtils.isEmpty(id)) {
            if (id.length() > 10)
                id = id.substring(0, 10);
            appendText(details, "Comment in");
            details.append(' ');
            details.monospace(id);
            details.append(':').append('\n');
        }
        appendComment(details, comment);
    }

    private void appendText(final StyledText details, String text) {
        if (text == null)
            return;
        text = text.trim();
        if (text.length() == 0)
            return;

        details.append(text);
    }

    private StyledText boldActor(final StyledText text, final GithubEvent event) {
        return boldUser(text, event.actor);
    }

    private StyledText boldUser(final StyledText text, final User user) {
        if (user != null)
            text.bold(user.login);
        return text;
    }

    private StyledText boldRepo(final StyledText text, final GithubEvent event) {
        Repo repo = event.repo;
        if (repo != null)
            text.bold(repo.name);
        return text;
    }

    private StyledText boldRepoName(final StyledText text,
                                    final GithubEvent event) {
        Repo repo = event.repo;
        if (repo != null) {
            String name = repo.name;
            if (!TextUtils.isEmpty(name)) {
                int slash = name.indexOf('/');
                if (slash != -1 && slash + 1 < name.length())
                    text.bold(name.substring(slash + 1));
            }
        }
        return text;
    }

}
