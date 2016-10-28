package com.yuyh.github.ui.main;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class MainPresenter implements MainContract.Presenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }


    @Override
    public void requestToken(String code, String clientId, String secret, String redirectUri) {

    }
}
