package com.yuyh.github.api.repo;

import com.yuyh.github.bean.resp.Branch;
import com.yuyh.github.bean.resp.CompareCommit;
import com.yuyh.github.bean.resp.Content;
import com.yuyh.github.bean.resp.Contributor;
import com.yuyh.github.bean.resp.GithubEvent;
import com.yuyh.github.bean.resp.GithubStatusResponse;
import com.yuyh.github.bean.resp.Release;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.bean.resp.RepoRequest;
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
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface RepoService {


    @GET("/repos/{owner}/{name}/contents")
    void contents(@Path("owner") String owner, @Path("name") String repo,
                  Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/contents")
    void contentsByRef(@Path("owner") String owner, @Path("name") String repo,
                       @Query("ref") String ref, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void contents(@Path("owner") String owner, @Path("name") String repo, @Path("path") String path,
                  Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/contents/{path}")
    void contentsByRef(@Path("owner") String owner, @Path("name") String repo,
                       @Path("path") String path, @Query("ref") String ref, Callback<List<Content>> callback);

    @GET("/repos/{owner}/{name}/stats/contributors")
    void contributors(@Path("owner") String owner, @Path("name") String repo,
                      Callback<List<Contributor>> callback);

    @GET("/repos/{owner}/{name}/stats/contributors")
    void contributors(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page,
                      Callback<List<Contributor>> callback);

    @GET("/repos/{owner}/{name}/collaborators")
    void collaborators(@Path("owner") String owner, @Path("name") String repo,
                       Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/collaborators")
    void collaborators(@Path("owner") String owner, @Path("name") String repo,
                       @Query("page") int page, Callback<List<User>> callback);

    @GET("/repos/{owner}/{name}/releases")
    void releases(@Path("owner") String owner, @Path("name") String repo,
                  Callback<List<Release>> callback);

    @GET("/repos/{owner}/{name}/releases")
    void releases(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page,
                  Callback<List<Release>> callback);

    @GET("/repos/{owner}/{name}/events")
    Observable<List<GithubEvent>> events(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/events")
    Observable<List<GithubEvent>> events(@Path("owner") String owner, @Path("name") String repo,
                                         @Query("page") int page);

    @GET("/repos/{owner}/{name}/forks")
    void listForks(@Path("owner") String owner, @Path("name") String repo, @Query("sort") String sort,
                   Callback<List<Repo>> callback);

    @GET("/repos/{owner}/{name}/forks")
    void listForks(@Path("owner") String owner, @Path("name") String repo, @Query("sort") String sort,
                   @Query("page") int page, Callback<List<Repo>> callback);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    void combinedStatusASync(@Path("owner") String owner, @Path("name") String repo,
                             @Path("ref") String ref, Callback<GithubStatusResponse> callback);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    void combinedStatusASync(@Path("owner") String owner, @Path("name") String repo,
                             @Path("ref") String ref, @Query("page") int page, Callback<GithubStatusResponse> callback);

    @GET("/repos/{owner}/{name}/branches")
    void branches(@Path("owner") String owner, @Path("name") String repo,
                  Callback<List<Branch>> callback);

    @GET("/repos/{owner}/{name}/branches")
    void branches(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page,
                  Callback<List<Branch>> callback);

    //Sync
    @GET("/repos/{owner}/{name}")
    Observable<Repo> get(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/contents")
    List<Content> contents(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/contents")
    List<Content> contentsByRef(@Path("owner") String owner, @Path("name") String repo,
                                @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/readme")
    Observable<Content> readme(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/readme")
    Observable<Content> readme(@Path("owner") String owner, @Path("name") String repo,
                               @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/contents/{path}")
    List<Content> contents(@Path("owner") String owner, @Path("name") String repo,
                           @Path("path") String path);

    @GET("/repos/{owner}/{name}/contents/{path}")
    List<Content> contentsByRef(@Path("owner") String owner, @Path("name") String repo,
                                @Path("path") String path, @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/stats/contributors")
    List<Contributor> contributors(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/stats/contributors")
    List<Contributor> contributors(@Path("owner") String owner, @Path("name") String repo,
                                   @Query("page") int page);

    @GET("/repos/{owner}/{name}/collaborators")
    List<User> collaborators(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/collaborators")
    List<User> collaborators(@Path("owner") String owner, @Path("name") String repo,
                             @Query("page") int page);

    @GET("/repos/{owner}/{name}/releases")
    List<Release> releases(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/releases")
    List<Release> releases(@Path("owner") String owner, @Path("name") String repo,
                           @Query("page") int page);

    @GET("/repos/{owner}/{name}/releases/latest")
    Observable<Release> lastRelease(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/releases/{id}")
    Observable<Release> release(@Path("owner") String owner, @Path("name") String repo,
                                @Path("id") String id);

    @GET("/repos/{owner}/{name}/compare/{base}...{head}")
    Observable<CompareCommit> compareCommits(@Path("owner") String owner, @Path("name") String repo,
                                             @Path("base") String base, @Path("head") String head);

    @DELETE("/repos/{owner}/{name}")
    Observable<Response> delete(@Path("owner") String owner, @Path("name") String repo);

    @GET("/repos/{owner}/{name}/languages")
    Observable<Map<String, Long>> languages(@Path("owner") String owner, @Path("name") String repo);

    @PATCH("/repos/{owner}/{name}")
    Observable<Repo> edit(@Path("owner") String owner, @Path("name") String repo,
                          @Body RepoRequest repoRequestDTO);

    @GET("/repos/{owner}/{name}/forks")
    List<Repo> listForks(@Path("owner") String owner, @Path("name") String repo,
                         @Query("sort") String sort);

    @GET("/repos/{owner}/{name}/forks")
    List<Repo> listForks(@Path("owner") String owner, @Path("name") String repo,
                         @Query("sort") String sort, @Query("page") int page);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    Observable<GithubStatusResponse> combinedStatus(@Path("owner") String owner,
                                                    @Path("name") String repo, @Path("ref") String ref);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    Observable<GithubStatusResponse> combinedStatus(@Path("owner") String owner,
                                                    @Path("name") String repo, @Path("ref") String ref, @Query("page") int page);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    GithubStatusResponse combinedStatusSync(@Path("owner") String owner, @Path("name") String repo,
                                            @Path("ref") String ref);

    @GET("/repos/{owner}/{name}/commits/{ref}/status")
    GithubStatusResponse combinedStatusSync(@Path("owner") String owner, @Path("name") String repo,
                                            @Path("ref") String ref, @Query("page") int page);

    @POST("/user/repos")
    Observable<Repo> create(@Body RepoRequest repoRequestDTO);

    //Async
    @GET("/repos/{owner}/{name}")
    Observable<Repo> getObs(@Path("owner") String owner, @Path("name") String repo);
}