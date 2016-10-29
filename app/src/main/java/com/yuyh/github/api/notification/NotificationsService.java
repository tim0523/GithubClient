package com.yuyh.github.api.notification;

import com.yuyh.github.bean.resp.LastDate;
import com.yuyh.github.bean.resp.Notification;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface NotificationsService {

    //obs
    @GET("/notifications")
    Observable<List<Notification>> getNotifications(@Query("all") boolean all,
                                                    @Query("participating") boolean participating);

    @PUT("/repos/{owner}/{name}/notifications")
    Observable<Response> markAsReadRepo(@Path("owner") String owner, @Path("name") String repo);

    @PUT("/repos/{owner}/{name}/notifications")
    Observable<Response> markAsReadRepo(@Path("owner") String owner, @Path("name") String repo,
                                        @Body LastDate body);

    @PATCH("/notifications/threads/{id}")
    Observable<Response> markThreadAsRead(@Path("id") String id, @Body Object empty);

    @PUT("/notifications/threads/{id}/subscription")
    Observable<Response> subscribeThread(@Path("id") String id,
                                         @Query("subscribed") boolean subscribed, @Query("ignored") boolean ignored);

    @DELETE("/notifications/threads/{id}/subscription")
    Observable<Response> unsubscribeThread(@Path("id") String id);
}