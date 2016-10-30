package com.yuyh.github.api.user;

import com.yuyh.github.bean.resp.Email;
import com.yuyh.github.bean.resp.User;

import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public interface UsersService {

    // Followers
    @GET("/user/followers")
    Observable<List<User>> followers();

    @GET("/users/{username}/followers")
    Observable<List<User>> followers(@Path("username") String username);

    @GET("/user/followers")
    Observable<List<User>> followers(@Query("page") int page);

    @GET("/users/{username}/followers")
    Observable<List<User>> followers(@Path("username") String username, @Query("page") int page);

    // Following
    @GET("/user/following")
    Observable<List<User>> following();

    @GET("/users/{username}/following")
    Observable<List<User>> following(@Path("username") String username);

    @GET("/user/following")
    Observable<List<User>> following(@Query("page") int page);

    @GET("/users/{username}/following")
    Observable<List<User>> following(@Path("username") String username, @Query("page") int page);

    //ORGS MEMBERS

    @GET("/orgs/{org}/members")
    void orgMembers(@Path("org") String org, Callback<List<User>> callback);

    @GET("/orgs/{org}/members")
    void orgMembers(@Path("org") String org, @Query("page") int page, Callback<List<User>> callback);

    //Sync
    @GET("/users/{user}")
    Observable<User> getSingleUser(@Path("user") String user);

    @GET("/user/emails")
    Observable<List<Email>> userEmails();

    @GET("/user")
    Observable<User> me();

    // FOLLOWING USER

    @GET("/user/following/{username}")
    Observable<Response> checkFollowing(@Path("username") String username);

    @PUT("/user/following/{username}")
    Observable<Response> followUser(@Body String empty, @Path("username") String username);

    @DELETE("/user/following/{username}")
    Observable<Response> unfollowUser(@Path("username") String username);
}