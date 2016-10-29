package com.yuyh.github.api.content;

import com.yuyh.github.bean.resp.Content;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface ContentService {

    //Sync
    @POST("/markdown/raw")
    Observable<String> markdown(@Body String readme);

    @GET("/repos/{owner}/{name}/contents/{path}")
    Observable<Content> fileContent(@Path("owner") String owner, @Path("name") String repo,
                                    @Path(value = "path") String path);

    @GET("/repos/{owner}/{name}/contents/{path}")
    Observable<Content> fileContentSha(@Path("owner") String owner, @Path("name") String repo,
                                       @Path(value = "path") String path, @Query("sha") String sha);

    @GET("/repos/{owner}/{name}/contents/{path}")
    Observable<Content> fileContentRef(@Path("owner") String owner, @Path("name") String repo,
                                       @Path(value = "path") String path, @Query("ref") String ref);

    @GET("/repos/{owner}/{name}/{file_type}/{path}")
    Observable<Object> archiveLink(@Path("owner") String owner, @Path("name") String repo,
                                   @Path("file_type") String file_type, @Path(value = "path") String path);
}