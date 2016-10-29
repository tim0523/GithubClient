package com.yuyh.github.api.events;

import com.yuyh.github.bean.resp.GithubEvent;

import java.util.List;

import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsService {

    //Async
    @GET("/users/{username}/received_events")
    void events(@Path("username") String username, Callback<List<GithubEvent>> eventsCallback);

    @GET("/users/{username}/received_events")
    void events(@Path("username") String username, @Query("page") int page,
                Callback<List<GithubEvent>> eventsCallback);

    @GET("/users/{username}/events")
    void createdEvents(@Path("username") String username, Callback<List<GithubEvent>> eventsCallback);

    @GET("/users/{username}/events")
    void createdEvents(@Path("username") String username, @Query("page") int page,
                       Callback<List<GithubEvent>> eventsCallback);

    //Sync
    @GET("/users/{username}/received_events")
    List<GithubEvent> events(@Path("username") String username);

    @GET("/users/{username}/received_events")
    List<GithubEvent> events(@Path("username") String username, @Query("page") int page);

    @GET("/users/{username}/events")
    List<GithubEvent> createdEvents(@Path("username") String username);

    @GET("/users/{username}/events")
    List<GithubEvent> createdEvents(@Path("username") String username, @Query("page") int page);
}