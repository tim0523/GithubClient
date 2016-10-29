package com.yuyh.github.api.search;

import com.yuyh.github.bean.resp.IssuesSearch;
import com.yuyh.github.bean.resp.ReposSearch;
import com.yuyh.github.bean.resp.UsersSearch;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchService {

    //Async
    @GET("/search/repositories")
    void repos(@Query("q") String query, Callback<ReposSearch> callback);

    @GET("/search/repositories")
    void repos(@Query("q") String query, @Query("page") int page, Callback<ReposSearch> callback);

    @GET("/search/issues")
    void issues(@Query("q") String query, Callback<IssuesSearch> callback);

    @GET("/search/issues")
    void issues(@Query("q") String query, @Query("page") int page, Callback<IssuesSearch> callback);

    @GET("/search/users")
    void users(@Query("q") String query, Callback<UsersSearch> callback);

    @GET("/search/users")
    void users(@Query("q") String query, @Query("page") int page, Callback<UsersSearch> callback);
}