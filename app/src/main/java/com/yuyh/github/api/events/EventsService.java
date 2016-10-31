package com.yuyh.github.api.events;

import com.yuyh.github.bean.resp.GithubEvent;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface EventsService {

    //Async
    @GET("/users/{username}/received_events")
    Observable<List<GithubEvent>> events(@Path("username") String username);

    @GET("/users/{username}/received_events")
    Observable<List<GithubEvent>> events(@Path("username") String username, @Query("page") int page);

    @GET("/users/{username}/events")
    Observable<List<GithubEvent>> createdEvents(@Path("username") String username);

    @GET("/users/{username}/events")
    Observable<List<GithubEvent>> createdEvents(@Path("username") String username, @Query("page") int page);
}