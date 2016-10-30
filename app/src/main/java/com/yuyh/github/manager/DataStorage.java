package com.yuyh.github.manager;

import com.yuyh.github.bean.resp.User;
import com.yuyh.github.utils.SPManager;

/**
 * @author yuyh.
 * @date 2016/10/29.
 */
public class DataStorage {

    public static final String KEY_URL = "KEY_URL";
    private static final String USER_NAME = DataStorage.class.getSimpleName() + ".USER_NAME";
    private static final String USER_TOKEN = DataStorage.class.getSimpleName() + ".USER_TOKEN";
    private static final String USER = DataStorage.class.getSimpleName() + ".USER";

    private static DataStorage instance;

    private DataStorage() {

    }

    public static DataStorage getInstance() {
        return instance == null ? instance = new DataStorage() : instance;
    }

    public void saveUserToken(String accessToken) {
        SPManager.getInstance().putString(USER_TOKEN, accessToken);
    }

    public String getUserToken() {
        return SPManager.getInstance().getString(USER_TOKEN, null);
    }

    public void saveUsername(String name) {
        SPManager.getInstance().putString(USER_NAME, name);
    }

    public String getUserName() {
        return SPManager.getInstance().getString(USER_NAME, null);
    }

    public void saveUrl(String url) {
        SPManager.getInstance().putString(KEY_URL, url);
    }

    public String getUrl() {
        return SPManager.getInstance().getString(KEY_URL, null);
    }

    public void saveUserInfo(User user) {
        SPManager.getInstance().putObject(USER, user);
    }

    public User getUserInfo() {
        return SPManager.getInstance().getObject(USER, User.class);
    }

    public void clear() {
        SPManager.getInstance().remove(KEY_URL);
        SPManager.getInstance().remove(USER_NAME);
        SPManager.getInstance().remove(USER_TOKEN);
    }
}
