package com.yuyh.github.api.git;

import com.yuyh.github.bean.resp.GitBlob;
import com.yuyh.github.bean.resp.GitCommit;
import com.yuyh.github.bean.resp.GitReference;
import com.yuyh.github.bean.resp.GitTree;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface GitDataService {

    //Async
    //Reference
    @GET("/repos/{owner}/{repo}/git/refs")
    void repoReferences(@Path("owner") String owner, @Path("repo") String name,
                        Callback<List<GitReference>> callback);

    @GET("/repos/{owner}/{repo}/git/refs")
    void repoReferences(@Path("owner") String owner, @Path("repo") String name,
                        @Query("page") int page, Callback<List<GitReference>> callback);

    //Sync
    @GET("/repos/{owner}/{repo}/git/{ref}")
    Observable<GitReference> repoReference(@Path("owner") String owner, @Path("repo") String name,
                                           @Path(value = "ref") String ref);

    @GET("/repos/{owner}/{repo}/git/commits/{sha}")
    Observable<GitCommit> repoCommit(@Path("owner") String owner, @Path("repo") String name,
                                     @Path("sha") String sha);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}")
    Observable<GitTree> repoTree(@Path("owner") String owner, @Path("repo") String name,
                                 @Path("sha") String sha);

    @GET("/repos/{owner}/{repo}/git/trees/{sha}?recursive=1")
    Observable<GitTree> repoTreeRecursive(@Path("owner") String owner, @Path("repo") String name,
                                          @Path("sha") String sha);

    @GET("/repos/{owner}/{repo}/git/blobs/{sha}")
    Observable<GitBlob> repoBlob(@Path("owner") String owner, @Path("repo") String name,
                                 @Path("sha") String sha);
}