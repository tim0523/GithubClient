package com.yuyh.github.api.login;

import com.yuyh.github.bean.AccessToken;
import com.yuyh.github.bean.CreateAuthorization;
import com.yuyh.github.bean.GithubAuthorization;
import com.yuyh.github.bean.RequestToken;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public interface LoginService {

    @POST("/login/oauth/access_token")
    Observable<AccessToken> requestToken(@Body RequestToken requestToken);

    @POST("/authorizations")
    Observable<GithubAuthorization> createAuthorization(@Body CreateAuthorization createAuthorization);
}
