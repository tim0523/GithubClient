package com.yuyh.github.api.repo;

import com.yuyh.github.bean.resp.Repo;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface RepoService {

    //Async
    // User repositories
    @GET("/user/repos?type=owner")
    Observable<List<Repo>> userReposList(@Query("sort") String sort);

    @GET("/user/repos?type=owner")
    void userReposList(@Query("page") int page, @Query("sort") String sort,
                       Callback<List<Repo>> callback);

    /**
     * @param username
     * @param sort     Can be one of created, updated, pushed, full_name. Default: full_name
     */
    @GET("/users/{username}/repos?type=owner")
    Observable<List<Repo>> userReposList(@Path("username") String username, @Query("sort") String sort);

    @GET("/users/{username}/repos?type=owner")
    void userReposList(@Path("username") String username, @Query("page") int page,
                       @Query("sort") String sort, Callback<List<Repo>> callback);

    @GET("/user/repos?affiliation=organization_member")
    void userReposListFromOrgs(@Query("sort") String sort, Callback<List<Repo>> callback);

    @GET("/user/repos?affiliation=organization_member")
    void userReposListFromOrgs(@Query("page") int page, @Query("sort") String sort,
                               Callback<List<Repo>> callback);

    @GET("/orgs/{org}/repos?type=all")
    void orgsReposList(@Path("org") String org, @Query("sort") String sort,
                       Callback<List<Repo>> callback);

    @GET("/orgs/{org}/repos?type=all")
    void orgsReposList(@Path("org") String org, @Query("page") int page, @Query("sort") String sort,
                       Callback<List<Repo>> callback);

    // Starred repos
    @GET("/user/starred?sort=updated")
    void userStarredReposList(@Query("sort") String sort, Callback<List<Repo>> callback);

    @GET("/user/starred?sort=updated")
    void userStarredReposList(@Query("page") int page, @Query("sort") String sort,
                              Callback<List<Repo>> callback);

    @GET("/users/{username}/starred?sort=updated")
    void userStarredReposList(@Path("username") String username, @Query("sort") String sort,
                              Callback<List<Repo>> callback);

    @GET("/users/{username}/starred?sort=updated")
    void userStarredReposList(@Path("username") String username, @Query("page") int page,
                              @Query("sort") String sort, Callback<List<Repo>> callback);

    // Wathched repos
    @GET("/user/subscriptions")
    void userSubscribedReposList(Callback<List<Repo>> callback);

    @GET("/user/subscriptions")
    void userSubscribedReposList(@Query("page") int page, Callback<List<Repo>> callback);

    @GET("/users/{username}/subscriptions")
    void userSubscribedReposList(@Path("username") String username, Callback<List<Repo>> callback);

    @GET("/users/{username}/subscriptions")
    void userSubscribedReposList(@Path("username") String username, @Query("page") int page,
                                 Callback<List<Repo>> callback);

    // Member
    @GET("/user/repos?affiliation=collaborator,organization_member")
    void userMemberRepos(Callback<List<Repo>> callback);

    // Member
    @GET("/user/repos?affiliation=collaborator,organization_member")
    void userMemberRepos(@Query("page") int page, Callback<List<Repo>> callback);

    //Sync
    @GET("/repos/{owner}/{name}/collaborators/{username}")
    Observable<Response> checkIfUserIsCollaborator(@Path("owner") String owner,
                                                   @Path("name") String repo,
                                                   @Path("username") String username);
}