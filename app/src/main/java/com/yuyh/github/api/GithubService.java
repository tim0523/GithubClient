package com.yuyh.github.api;

import com.yuyh.github.bean.AccessToken;
import com.yuyh.github.bean.RequestToken;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public interface GithubService {

    @POST("/login/oauth/access_token")
    Observable<AccessToken> requestToken(@Body RequestToken requestToken);

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
