package com.yuyh.github.api.gitignore;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;

public interface GitIgnoreService {

    //Sync
    @GET("/gitignore/templates")
    Observable<ArrayList<String>> list();
}