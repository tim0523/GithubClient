package com.yuyh.github.ui.auth;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.yuyh.github.R;
import com.yuyh.github.base.BaseAppCompatActivity;

import butterknife.Bind;
import okhttp3.HttpUrl;

/**
 * @author yuyh.
 * @date 2016/10/28.
 */
public class LoginActivity extends BaseAppCompatActivity {

    @Bind(R.id.web_view)
    WebView mWebView;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViewsAndEvents() {

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals(getString(R.string.github_oauth_scheme))) {
                    Intent data = new Intent();
                    data.setData(uri);
                    setResult(RESULT_OK, data);
                    finish();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }
        });

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                mProgressBar.setProgress(newProgress);
                super.onProgressChanged(view, newProgress);
            }
        });

        mWebView.loadUrl(generateUrl());
    }

    private String generateUrl() {

        String scope = "user,public_repo,repo,delete_repo,notifications,gist";
        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("https")
                .host("github.com")
                .addPathSegment("login")
                .addPathSegment("oauth")
                .addPathSegment("authorize")
                .addQueryParameter("client_id", getString(R.string.github_client))
                .addQueryParameter("scope", scope);

        return url.toString();
    }
}
