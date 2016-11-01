package com.yuyh.github.ui.repos.readme;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.Repo;

import butterknife.Bind;

/**
 * @author yuyh.
 * @date 2016/11/1.
 */
public class ReadmeFragment extends BaseLazyFragment implements ReadmeContract.View {

    public final static String BUNDLE_REPO = "repo";

    public static ReadmeFragment instance(Repo repo) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(BUNDLE_REPO, repo);
        ReadmeFragment fragment = new ReadmeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Bind(R.id.wvReadme)
    WebView mWvReadme;

    private ReadmePresenter mPresenter;
    private Repo repo;

    private static final String PAGE_START = "<!DOCTYPE html><html lang=\"en\"> <head> <title></title>" +
            "<meta charset=\"UTF-8\"> " +
            "<meta name=\"viewport\" content=\"width=device-width; initial-scale=1.0; maximum-scale=1.0; user-scalable=0;\"/>" +
            "<script src=\"intercept.js\"></script>" +
            "<link href=\"github.css\" rel=\"stylesheet\"> </head> <body>";

    private static final String PAGE_END = "</body></html>";

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_readme);

        showLoadding();
        if ((repo = (Repo) getArguments().getSerializable(BUNDLE_REPO)) != null) {
            mPresenter = new ReadmePresenter(this);
            mPresenter.getReadme(repo);
        }
    }

    @Override
    public void showReadme(String content) {
        WebSettings settings = mWvReadme.getSettings();
        settings.setJavaScriptEnabled(true);
        mWvReadme.addJavascriptInterface(this, "Readme");
        mWvReadme.loadDataWithBaseURL("file:///android_asset/", PAGE_START + content + PAGE_END, "text/html", "UTF-8", null);
        hideLoadding();
    }
}
