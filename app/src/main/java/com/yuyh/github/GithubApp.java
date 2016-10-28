package com.yuyh.github;

import android.app.Application;
import android.content.Context;

import com.yuyh.github.utils.SPManager;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class GithubApp extends Application {

    private static Context sInstance;

    public static Context getsInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        SPManager.init(this, getPackageName() + "_preference", Context.MODE_PRIVATE);
    }
}
