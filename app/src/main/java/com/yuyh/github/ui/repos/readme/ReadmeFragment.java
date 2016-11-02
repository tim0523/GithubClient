package com.yuyh.github.ui.repos.readme;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseLazyFragment;
import com.yuyh.github.bean.resp.Repo;
import com.yuyh.github.utils.FileUtils;

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
        String md = FileUtils.readAssetsFile("readme.html")
                .replaceAll("\\r\\n", "")
                .replaceAll("\\n", "");
        String uri = "file:///android_asset/";
        String mime = "text/html";
        String charset = "UTF-8";
        mWvReadme.loadDataWithBaseURL(uri, String.format(md, content), mime, charset, null);
        hideLoadding();
    }
}
