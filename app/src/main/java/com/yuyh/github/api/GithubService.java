package com.yuyh.github.api;

import retrofit2.Response;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public interface GithubService {



    @GET("/gists/{id}/star")
    Observable<Response> checkIfStarred(@Path("id") String id);

    @PUT("/gists/{id}/star")
    Observable<Response> starGist(@Path("id") String id);

    @DELETE("/gists/{id}/star")
    Observable<Response> unstarGist(@Path("id") String id);

    @DELETE("/gists/{id}")
    Observable<Response> deleteGist(@Path("id") String id);

    /**
     * Get's readme content.
     * Note it's a HTTP HEAD so only headers will be returned for checking the existences.
     *
     * @param owner
     * @param repo
     * @return
     */
    @HEAD("/repos/{owner}/{repo}/readme")
    Observable<Response> hasReadme(@Path("owner") String owner, @Path("repo") String repo);
}
