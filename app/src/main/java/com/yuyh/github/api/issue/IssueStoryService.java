package com.yuyh.github.api.issue;

import com.yuyh.github.bean.IssueRequest;
import com.yuyh.github.bean.resp.GithubComment;
import com.yuyh.github.bean.resp.Issue;
import com.yuyh.github.bean.resp.IssueEvent;
import com.yuyh.github.bean.resp.IssueStory;
import com.yuyh.github.bean.resp.Label;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface IssueStoryService {

    //Async
    @POST("/repos/{owner}/{name}/issues")
    void create(@Path("owner") String owner, @Path("name") String repo, @Body IssueRequest issue,
                Callback<IssueStory> callback);

    @GET("/repos/{owner}/{name}/issues/{num}")
    void detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                Callback<Issue> issueCallback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                  Callback<List<GithubComment>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    void comments(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                  @Query("page") int page, Callback<List<GithubComment>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                Callback<List<IssueEvent>> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    void events(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                @Query("page") int page, Callback<List<IssueEvent>> callback);

    @PATCH("/repos/{owner}/{name}/issues/{num}")
    void closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                    @Body IssueRequest issueRequest, Callback<Issue> callback);

    @POST("/repos/{owner}/{name}/issues/{num}/comments")
    void addComment(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                    @Body GithubComment comment, Callback<GithubComment> callback);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                Callback<List<Label>> issueCallback);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    void labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                @Query("page") int page, Callback<List<Label>> issueCallback);

    //Sync
    @POST("/repos/{owner}/{name}/issues")
    IssueStory create(@Path("owner") String owner, @Path("name") String repo,
                      @Body IssueRequest issue);

    @GET("/repos/{owner}/{name}/issues/{num}")
    Issue detail(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo,
                                 @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    List<GithubComment> comments(@Path("owner") String owner, @Path("name") String repo,
                                 @Path("num") int num, @Query("page") int page);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<IssueEvent> events(@Path("owner") String owner, @Path("name") String repo,
                            @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/events")
    List<IssueEvent> events(@Path("owner") String owner, @Path("name") String repo,
                            @Path("num") int num, @Query("page") int page);

    @PATCH("/repos/{owner}/{name}/issues/{num}")
    Issue closeIssue(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                     @Body IssueRequest issueRequest);

    @POST("/repos/{owner}/{name}/issues/{num}/comments")
    GithubComment addComment(@Path("owner") String owner, @Path("name") String repo,
                             @Path("num") int num, @Body GithubComment comment);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/labels")
    List<Label> labels(@Path("owner") String owner, @Path("name") String repo, @Path("num") int num,
                       @Query("page") int page);

    // Observable

    @GET("/repos/{owner}/{name}/issues/{num}")
    Observable<Issue> detailObs(@Path("owner") String owner, @Path("name") String repo,
                                @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    Observable<List<GithubComment>> commentsObs(@Path("owner") String owner,
                                                @Path("name") String repo, @Path("num") int num);

    @GET("/repos/{owner}/{name}/issues/{num}/comments")
    Observable<List<GithubComment>> commentsObs(@Path("owner") String owner,
                                                @Path("name") String repo, @Path("num") int num, @Query("page") int page);
}