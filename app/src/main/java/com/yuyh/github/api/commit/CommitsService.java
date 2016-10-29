package com.yuyh.github.api.commit;

import com.yuyh.github.bean.resp.Commit;
import com.yuyh.github.bean.resp.CommitComment;
import com.yuyh.github.bean.resp.CommitCommentRequest;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface CommitsService {

  //Async

  @GET("/repos/{owner}/{name}/commits/{sha}/comments")
  void singleCommitComments(@Path("owner") String owner, @Path("name") String repo,
                            @Path("sha") String sha, Callback<List<CommitComment>> callback);

  @GET("/repos/{owner}/{name}/commits/{sha}/comments")
  void singleCommitComments(@Path("owner") String owner, @Path("name") String repo,
                            @Path("sha") String sha, @Query("page") int page, Callback<List<CommitComment>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commits(@Path("owner") String owner, @Path("name") String repo,
      Callback<List<Commit>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page,
      Callback<List<Commit>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commits(@Path("owner") String owner, @Path("name") String repo, @Query("sha") String sha,
      Callback<List<Commit>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commits(@Path("owner") String owner, @Path("name") String repo, @Query("page") int page,
      @Query("sha") String sha, Callback<List<Commit>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commitsByPath(@Path("owner") String owner, @Path("name") String repo,
      @Query("path") String path, Callback<List<Commit>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commitsByPath(@Path("owner") String owner, @Path("name") String repo,
      @Query("path") String path, @Query("page") int page, Callback<List<Commit>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commitsByPath(@Path("owner") String owner, @Path("name") String repo,
      @Query("path") String path, @Query("sha") String sha, Callback<List<Commit>> callback);

  @GET("/repos/{owner}/{name}/commits")
  void commitsByPath(@Path("owner") String owner, @Path("name") String repo,
      @Query("path") String path, @Query("sha") String sha, @Query("page") int page,
      Callback<List<Commit>> callback);

  //Sync
  @GET("/repos/{owner}/{name}/commits/{sha}")
  Observable<Commit> singleCommit(@Path("owner") String owner, @Path("name") String repo,
                                  @Path("sha") String sha);

  @POST("/repos/{owner}/{name}/commits/{sha}/comments")
  Observable<CommitComment> publishComment(@Path("owner") String owner, @Path("name") String repo,
      @Path("sha") String sha, @Body CommitCommentRequest request);
}