package com.yuyh.github.api.issue;

import com.yuyh.github.bean.CommentRequest;
import com.yuyh.github.bean.CreateMilestoneRequestDTO;
import com.yuyh.github.bean.IssueRequest;
import com.yuyh.github.bean.resp.GithubComment;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.Issue;
import com.yuyh.github.bean.resp.Label;
import com.yuyh.github.bean.resp.Milestone;
import com.yuyh.github.bean.resp.User;

import java.util.List;
import java.util.Map;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface IssuesService {

    //Async
    @GET("/repos/{owner}/{name}/issues?sort=updated")
    void issues(@Path("owner") String owner, @Path("name") String repo,
                @QueryMap Map<String, String> filter, Callback<List<Issue>> callback);

    @GET("/repos/{owner}/{name}/issues?sort=updated")
    void issues(@Path("owner") String owner, @Path("name") String repo,
                @QueryMap Map<String, String> filter, @Query("page") int page,
                Callback<List<Issue>> callback);

    @GET("/issues")
    void issues(@QueryMap Map<String, String> filter, Callback<List<Issue>> callback);

    @GET("/issues")
    void issues(@QueryMap Map<String, String> filter, @Query("page") int page,
                Callback<List<Issue>> callback);

    @GET("/issues")
    void userIssues(Callback<List<Issue>> callback);

    @GET("/issues")
    void userIssues(@Query("page") int page,
                    Callback<List<Issue>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                  Callback<List<GithubComment>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                  @Query("page") int page, Callback<List<GithubComment>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                Callback<List<GithubEvent>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                @Query("page") int page, Callback<List<GithubEvent>> callback);

    @GET("/repos/{owner}/{name}/milestones")
        //State can be open, closed and all
    void milestones(@Path("owner") String owner, @Path("name") String repo,
                    @Query("state") String state, Callback<List<Milestone>> callback);

    @GET("/repos/{owner}/{name}/milestones")
        //State can be open, closed and all
    void milestones(@Path("owner") String owner, @Path("name") String repo,
                    @Query("state") String state, @Query("page") int page, Callback<List<Milestone>> callback);

    @GET("/repos/{owner}/{name}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo,
                Callback<List<Label>> callback);

    @GET("/repos/{owner}/{name}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page,
                Callback<List<Label>> callback);

    @GET("/repos/{owner}/{name}/assignees")
    void assignees(@Path("owner") String owner, @Path("name") String repo,
                   Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/assignees")
    void assignees(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page,
                   Callback<List<User>> callback);

    //Sync
    @GET("/repos/{owner}/{name}/issues?sort=updated")
    List<Issue> issues(@Path("owner") String owner, @Path("name") String repo,
                       @QueryMap Map<String, String> filter);

    @GET("/repos/{owner}/{name}/issues?sort=updated")
    List<Issue> issues(@Path("owner") String owner, @Path("name") String repo,
                       @QueryMap Map<String, String> filter, @Query("page") int page);

    @GET("/issues")
    List<Issue> issues(@QueryMap Map<String, String> filter);

    @GET("/issues")
    List<Issue> issues(@QueryMap Map<String, String> filter, @Query("page") int page);

    @POST("/repos/{owner}/{name}/issues")
    Observable<Issue> create(@Path("owner") String owner, @Path("name") String repo,
                             @Body IssueRequest issue);

    @GET("/repos/{owner}/{name}/issues/{num}")
    Observable<Issue> detail(@Path("owner") String owner, @Path("name") String repo,
                             @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo,
                                 @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo,
                                 @Path("num") int num, @Query("page") int page);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<GithubEvent> events(@Path("owner") String owner, @Path("name") String repo,
                             @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<GithubEvent> events(@Path("owner") String owner, @Path("name") String repo,
                             @Path("num") int num, @Query("page") int page);

    @PATCH("/repos/{owner}/{name}/issues/{num}")
    Observable<Issue> closeIssue(@Path("owner") String owner, @Path("name") String repo,
                                 @Path("num") int num, @Body IssueRequest issueRequest);

    @POST("/repos/{owner}/{name}/issues/{num}/comments")
    Observable<GithubComment> addComment(@Path("owner") String owner, @Path("name") String repo,
                                         @Path("num") int num, @Body GithubComment comment);

    @GET("/repos/{owner}/{name}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo,
                       @Query("page") int page);

    @POST("/repos/{owner}/{name}/milestones")
    Observable<Milestone> createMilestone(@Path("owner") String owner, @Path("name") String repo,
                                          @Body CreateMilestoneRequestDTO createMilestoneRequestDTO);

    @PATCH("/repos/{owner}/{name}/issues/{number}")
    Observable<Issue> editIssue(@Path("owner") String owner, @Path("name") String repo,
                                @Path("number") int number, @Body IssueRequest editIssueRequestDTO);

    @DELETE("/repos/{owner}/{name}/issues/comments/{id}")
    Observable<Response> deleteComment(@Path("owner") String owner, @Path("name") String name,
                                       @Path("id") String id);

    @PATCH("/repos/{owner}/{name}/issues/comments/{id}")
    Observable<GithubComment> editComment(@Path("owner") String owner, @Path("name") String name,
                                          @Path("id") String id, @Body CommentRequest body);
}
